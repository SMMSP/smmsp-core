package com.smmsp.core.net;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.nio.channels.SocketChannel;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.print.DocFlavor.BYTE_ARRAY;

import org.apache.log4j.Logger;

import com.smmsp.core.utils.ArrayUtils;

/**
 * This class wraps the URL connection applying a bit of FTP protocol logic to
 * make downloading files from FTP easier.
 * 
 * @author sean
 * 
 */
public class FTPConnection extends InternetConnection {

	private static Logger LOG = Logger.getLogger(FTPConnection.class);

	private static Pattern PASV_PATTERN = Pattern.compile(".+\\((.+)\\).*");
	
	private static int BUFFER_SIZE = 2048;
	
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
		int lastSlashPos = _path.lastIndexOf('/');
		_file = _path.substring(lastSlashPos + 1);
		_path = _path.substring(0, lastSlashPos);
				
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
				SocketChannel commandSock = SocketChannel.open();
				SocketChannel dataSock = SocketChannel.open();
			){
			
			commandSock.connect(new InetSocketAddress(_server, _port));
			
			// read status line
			readAndVerifyStatus("220", commandSock);
			
			// log in with user and password.
			sendCommand("USER " + _user + "\r\n", commandSock);
			
			// read user response
			readAndVerifyStatus("331", commandSock);
			
			// send password.
			sendCommand("PASS " + _pass + "\r\n", commandSock);
			
			// read pass response
			readAndVerifyStatus("230", commandSock);
			
			// change directory to target path
			sendCommand("CWD " + _path + "\r\n", commandSock);
			
			// read directory change successful.
			readAndVerifyStatus("250", commandSock);
			
			// send passive command
			sendCommand("PASV\r\n", commandSock);
			
			//227 Entering Passive Mode (192,0,32,8,39,237)
			String pasv = readAndVerifyStatus("227", commandSock).trim();
			Matcher match = PASV_PATTERN.matcher(pasv);
			if(!match.matches()){
				LOG.error("FTP cannot pull out ports for PASV command.");
				throw new ProtocolException("FTP cannot pull out ports for PASV command.");
			}
			String[] octets = match.group(1).split(",");
			int dataPort = Integer.valueOf(octets[4]) * 256 + Integer.valueOf(octets[5]);
			
			// request file
			sendCommand("RETR " + _file + "\r\n", commandSock);
			
			dataSock.connect(new InetSocketAddress(_server, dataPort));
			
			return new ByteArrayInputStream(consumeEntireChannel(dataSock));
			
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
		buf.rewind();
		out.write(buf);
	}
	
	protected String readAndVerifyStatus(String status, SocketChannel in) throws IOException{
		ByteBuffer buf = ByteBuffer.allocate(255);
		int read = in.read(buf);
		buf.rewind();
		String response = new String(buf.array(), "UTF-8");
		LOG.debug("FTP received: " + response);
		if(!response.startsWith(status)){
			String code = response.substring(0, 3);
			LOG.error("FTP unexpected status code: " + code);
			throw new ProtocolException("Unexpected status code: " + code);
		}
		return response;
	}
	
	protected byte[] consumeEntireChannel(SocketChannel in) throws IOException{
		byte[] combined = new byte[0];
		ByteBuffer buf = ByteBuffer.allocate(BUFFER_SIZE);
		
		while(in.read(buf) > 0){
			combined = ArrayUtils.combine(combined, buf.array());
		}
		return combined;
	}
}
