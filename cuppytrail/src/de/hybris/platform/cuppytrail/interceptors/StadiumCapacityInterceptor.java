/**
 *
 */
package de.hybris.platform.cuppytrail.interceptors;

import static de.hybris.platform.servicelayer.model.ModelContextUtils.getItemModelContext;

import de.hybris.platform.cuppytrail.events.CapacityEvent;
import de.hybris.platform.cuppytrail.model.StadiumModel;
import de.hybris.platform.servicelayer.event.EventService;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.PrepareInterceptor;
import de.hybris.platform.servicelayer.interceptor.ValidateInterceptor;

import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author Fernando Bangho
 *
 */
public class StadiumCapacityInterceptor implements ValidateInterceptor, PrepareInterceptor
{

	private static final int BIG_STADIUM = 50000;
	private static final int TOO_BIG_STADIUM = 100000;

	@Autowired
	private EventService eventService;

	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.platform.servicelayer.interceptor.PrepareInterceptor#onPrepare(java.lang.Object,
	 * de.hybris.platform.servicelayer.interceptor.InterceptorContext)
	 */
	@Override
	public void onPrepare(final Object arg0, final InterceptorContext arg1) throws InterceptorException
	{
		if (arg0 instanceof StadiumModel)
		{
			final StadiumModel stadium = (StadiumModel) arg0;
			if (hasBecomeBig(stadium, arg1))
			{
				eventService.publishEvent(new CapacityEvent(stadium.getCapacity(), stadium.getCode()));
			}
		}
	}

	/**
	 * @param stadium
	 * @param arg1
	 * @return
	 */
	private boolean hasBecomeBig(final StadiumModel stadium, final InterceptorContext ctx)
	{
		final Integer capacity = stadium.getCapacity();
		if (capacity != null && capacity.intValue() >= BIG_STADIUM && capacity.intValue() < TOO_BIG_STADIUM)
		{
			if (ctx.isNew(stadium))
			{
				return true;
			}
			else
			{
				final Integer oldValue = getItemModelContext(stadium).getOriginalValue(StadiumModel.CAPACITY);
				if (oldValue == null || oldValue.intValue() < 50000)
				{
					return true;
				}
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.platform.servicelayer.interceptor.ValidateInterceptor#onValidate(java.lang.Object,
	 * de.hybris.platform.servicelayer.interceptor.InterceptorContext)
	 */
	@Override
	public void onValidate(final Object arg0, final InterceptorContext arg1) throws InterceptorException
	{
		if (arg0 instanceof StadiumModel)
		{
			final StadiumModel stadiumModel = (StadiumModel) arg0;
			final Integer capacity = stadiumModel.getCapacity();
			if (capacity != null && capacity.intValue() >= TOO_BIG_STADIUM)
			{
				throw new InterceptorException("Capacity is too high");
			}
		}
	}

}
