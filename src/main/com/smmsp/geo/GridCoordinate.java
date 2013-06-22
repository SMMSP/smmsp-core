/*
 * GridCoordinate.java
 * 
 * Copyright (C) 2013 Sean P Madden
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 * 
 * If you would like to license this code under the GNU LGPL, please
 * see http://www.seanmadden.net/licensing for details.
 */
package com.smmsp.geo;

/**
 * Represents an X + Y + Z coordinate in a grid space.  This is simply
 * a data storage class and contains no substantial logic.
 *  
 * @author sean
 */
public abstract class GridCoordinate<T>
{
	
	protected T northing;
	protected T easting;
	protected int zone;
	protected T scaleFactor;
	
	/**
	 * Constructs a GridCoordinate object
	 * 
	 * @param northing the northing parameter
	 * @param easting the easting parameter
	 * @param zone the zone
	 * @param scaleFactor the scaleFactor;
	 */
	private GridCoordinate(
			final T northing,
			final T easting,
			final int zone,
			final T scaleFactor
			)
	{
		this.northing = northing;
		this.easting = easting;
		this.zone = zone;
		this.scaleFactor = scaleFactor;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((easting == null) ? 0 : easting.hashCode());
		result = prime * result
				+ ((northing == null) ? 0 : northing.hashCode());
		result = prime * result
				+ ((scaleFactor == null) ? 0 : scaleFactor.hashCode());
		result = prime * result + zone;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (obj == null)
		{
			return false;
		}
		if (!(obj instanceof GridCoordinate))
		{
			return false;
		}
		GridCoordinate<?> other = (GridCoordinate<?>) obj;
		if (easting == null)
		{
			if (other.easting != null)
			{
				return false;
			}
		} else if (!easting.equals(other.easting))
		{
			return false;
		}
		if (northing == null)
		{
			if (other.northing != null)
			{
				return false;
			}
		} else if (!northing.equals(other.northing))
		{
			return false;
		}
		if (scaleFactor == null)
		{
			if (other.scaleFactor != null)
			{
				return false;
			}
		} else if (!scaleFactor.equals(other.scaleFactor))
		{
			return false;
		}
		if (zone != other.zone)
		{
			return false;
		}
		return true;
	}

	/**
	 * @return the northing
	 */
	public T getNorthing()
	{
		return northing;
	}

	/**
	 * @param northing the northing to set
	 */
	public void setNorthing(T northing)
	{
		this.northing = northing;
	}

	/**
	 * @return the easting
	 */
	public T getEasting()
	{
		return easting;
	}

	/**
	 * @param easting the easting to set
	 */
	public void setEasting(T easting)
	{
		this.easting = easting;
	}

	/**
	 * @return the zone
	 */
	public int getZone()
	{
		return zone;
	}

	/**
	 * @param zone the zone to set
	 */
	public void setZone(int zone)
	{
		this.zone = zone;
	}

	/**
	 * @return the scaleFactor
	 */
	public T getScaleFactor()
	{
		return scaleFactor;
	}

	/**
	 * @param scaleFactor the scaleFactor to set
	 */
	public void setScaleFactor(T scaleFactor)
	{
		this.scaleFactor = scaleFactor;
	}

	/**
	 * GridCoordinate backed by a Float
	 * 
	 * @author sean
	 */
	public static class Float extends GridCoordinate<java.lang.Float>
	{
		
		/**
		 * Creates an empty GridCoordinate using a float initialized 
		 * to 0, 0, 0
		 */
		public Float()
		{
			super(0.0f, 0.0f, 0, 0.0f);
		}
		
		/**
		 * Creates a GridCoordinate using a float initialized to 
		 * ( Northing, Easting, Zone, 1.0f)
		 * 
		 * @param northing the northing parameter (y)
		 * @param easting the easting parameter (x)
		 * @param zone the universal zone (+N -S)
		 */
		public Float(
				final float northing, 
				final float easting, 
				final int zone)
		{
			super(northing, easting, zone, 1.0f);
		}
		
		/**
		 * Creates a GridCoordinate using a float initialized to
		 * (Northing, Easting, Zone, ScaleFactor)
		 * 
		 * @param northing the northing parameter (y)
		 * @param easting the easting parameter (x)
		 * @param zone the universal zone (+N -S)
		 * @param scaleFactor The unitless scale factor
		 */
		public Float(
				final float northing,
				final float easting,
				final int zone,
				final float scaleFactor)
		{
			super(northing, easting, zone, scaleFactor);
		}
		
		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString()
		{
			StringBuilder builder = new StringBuilder();
			builder.append("GridCoordinate.Float [northing=");
			builder.append(northing);
			builder.append(", easting=");
			builder.append(easting);
			builder.append(", zone=");
			builder.append(zone);
			builder.append(", scaleFactor=");
			builder.append(scaleFactor);
			builder.append("]");
			return builder.toString();
		}
	}
	
	public static class Double extends GridCoordinate<java.lang.Double>
	{
		/**
		 * Creates an empty GridCoordinate using a double initialized 
		 * to 0, 0, 0
		 */
		public Double()
		{
			super(0.0, 0.0, 0, 0.0);
		}
		
		/**
		 * Creates a GridCoordinate using a double initialized to 
		 * ( Northing, Easting, Zone, 1.0)
		 * 
		 * @param northing the northing parameter (y)
		 * @param easting the easting parameter (x)
		 * @param zone the universal zone (+N -S)
		 */
		public Double(
				final double northing, 
				final double easting, 
				final int zone)
		{
			super(northing, easting, zone, 1.0);
		}
		
		/**
		 * Creates a GridCoordinate using a double initialized to
		 * (Northing, Easting, Zone, ScaleFactor)
		 * 
		 * @param northing the northing parameter (y)
		 * @param easting the easting parameter (x)
		 * @param zone the universal zone (+N -S)
		 * @param scaleFactor The unitless scale factor
		 */
		public Double(
				final double northing,
				final double easting,
				final int zone,
				final double scaleFactor)
		{
			super(northing, easting, zone, scaleFactor);
		}
		
		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString()
		{
			StringBuilder builder = new StringBuilder();
			builder.append("GridCoordinate.Double [northing=");
			builder.append(northing);
			builder.append(", easting=");
			builder.append(easting);
			builder.append(", zone=");
			builder.append(zone);
			builder.append(", scaleFactor=");
			builder.append(scaleFactor);
			builder.append("]");
			return builder.toString();
		}
	}
}
