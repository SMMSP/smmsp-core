package com.smmsp.core.net;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

/**
 * This class wraps the URL connection applying a bit of FTP protocol logic to
 * make downloading files from FTP easier.
 * 
 * @author sean
 * 
 */
public class FTPConnection extends InternetConnection {

	private static Logger LOG = Logger.getLogger(FTPConnection.class);

	private static Pattern PASV_PATTERN = Pattern.compile(".+\\((.+)\\)");
	
	private String _user = "anonymous";
	private String _pass = "anonymous";
	private String _path = "";
	private String _file = "";
	private String _server = "";
	private int _port = 21;

	public FTPConnection(URL url) throws MalformedURLException {
		LOG.debug("Created FTPConnection Object with URL: " + url);

		if (!"ftp".equals(url.getProtocol().toLowerCase())) {
			throw new MalformedURLException("This is not an FTP url!");
		}
		if (url.getUserInfo() != null) {
			String userinfo = url.getUserInfo();
			String[] parts = userinfo.split(":");
			if (parts.length > 2) {
				throw new MalformedURLException();
			} else if (parts.length == 1) {
				_user = userinfo;
			} else if (parts.length == 2) {
				_user = parts[0];
				_pass = parts[1];
			}
		}
		_path = url.getPath();
		_file = url.getFile();
		_server = url.getHost();

		if (url.getPort() > 0) {
			_port = url.getPort();
		}
	}

	public FTPConnection(String url) throws MalformedURLException {
		this(new URL(url));
	}

	@Override
	public InputStream getDataStream() {
		try (
				SocketChannel commandSock = SocketChannel.open(new InetSocketAddress(_ser, _port));
				SocketChannel dataSock = SocketChannel.open();
			){
			
			BufferedReader cmdInputStream = new BufferedReader(
					new InputStreamReader(commandSock.getInputStream()));
			BufferedWriter cmdOutputStream = new BufferedWriter(
					new OutputStreamWriter(commandSock.getOutputStream()));

			// read status line
			readAndVerifyStatus("220", cmdInputStream);
			
			// log in with user and password.
			sendCommand("USER " + _user + "\r\n", cmdOutputStream);
			
			// read user response
			readAndVerifyStatus("331", cmdInputStream);
			
			// send password.
			sendCommand("PASS " + _pass + "\r\n", cmdOutputStream);
			
			// read pass response
			readAndVerifyStatus("230", cmdInputStream);
			
			// change directory to target path
			sendCommand("CWD " + _path + "\r\n", cmdOutputStream);
			
			// read directory change successful.
			readAndVerifyStatus("250", cmdInputStream);
			
			// send passive command
			sendCommand("PASV", cmdOutputStream);
			
			//227 Entering Passive Mode (192,0,32,8,39,237)
			String pasv = readAndVerifyStatus("227", cmdInputStream);
			Matcher match = PASV_PATTERN.matcher(pasv);
			if(!match.matches()){
				LOG.error("FTP cannot pull out ports for PASV command.");
				throw new ProtocolException("FTP cannot pull out ports for PASV command.");
			}
			String[] octets = match.group(1).split(",");
			int dataPort = Integer.valueOf(octets[4]) * 256 + Integer.valueOf(octets[5]);
			
			// request file
			sendCommand("RETR " + _file + "\r\n", cmdOutputStream);
			
			dataSock.connect(new InetSocketAddress(_server, dataPort));
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	protected void sendCommand(String cmd, SocketChannel out) throws IOException{
		LOG.debug("FTP sent: " + cmd);
		ByteBuffer buf = ByteBuffer.allocate(cmd.length());
		buf.put(cmd.getBytes());
		out.write(buf);
	}
	
	protected String readAndVerifyStatus(String status, SocketChannel in) throws IOException{
		String response = in.readLine();
		LOG.debug("FTP received: " + response);
		if(!response.startsWith(status)){
			String code = response.substring(0, 3);
			LOG.error("FTP unexpected status code: " + code);
			throw new ProtocolException("Unexpected status code: " + code);
		}
		return response;
	}
}
