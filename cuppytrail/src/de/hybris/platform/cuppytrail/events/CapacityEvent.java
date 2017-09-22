/**
 *
 */
package de.hybris.platform.cuppytrail.events;

import de.hybris.platform.servicelayer.event.ClusterAwareEvent;
import de.hybris.platform.servicelayer.event.events.AbstractEvent;


/**
 * @author Fernando Bangho
 *
 */
public class CapacityEvent extends AbstractEvent implements ClusterAwareEvent
{

	private final Integer capacity;
	private final String code;

	/**
	 *
	 */
	public CapacityEvent(final Integer capacity, final String code)
	{
		super();
		this.capacity = capacity;
		this.code = code;
	}

	/**
	 * @return the capacity
	 */
	public Integer getCapacity()
	{
		return capacity;
	}

	/**
	 * @return the code
	 */
	public String getCode()
	{
		return code;
	}

	@Override
	public String toString()
	{

		return this.code + "(" + this.capacity + ")";
	}

	@Override
	public boolean publish(final int sourceNodeId, final int targetNodeId)
	{
		return (sourceNodeId == targetNodeId);
	}
}
