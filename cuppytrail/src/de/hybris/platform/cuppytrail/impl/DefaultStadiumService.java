/**
 *
 */
package de.hybris.platform.cuppytrail.impl;

import de.hybris.platform.cuppytrail.StadiumService;
import de.hybris.platform.cuppytrail.daos.StadiumDAO;
import de.hybris.platform.cuppytrail.model.StadiumModel;
import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;

import java.util.List;

import org.springframework.beans.factory.annotation.Required;


/**
 * @author Fernando Bangho
 *
 */
public class DefaultStadiumService implements StadiumService
{

	private StadiumDAO stadiumDao;

	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.platform.cuppytrail.StadiumService#getStadiums()
	 */
	@Override
	public List<StadiumModel> getStadiums()
	{
		return stadiumDao.findStadiums();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.platform.cuppytrail.StadiumService#getStadiumsForCode(java.lang.String)
	 */
	@Override
	public StadiumModel getStadiumForCode(final String code) throws UnknownIdentifierException, AmbiguousIdentifierException
	{
		final List<StadiumModel> result = stadiumDao.findStadiumsByCode(code);
		if (result.isEmpty())
		{
			throw new UnknownIdentifierException("Stadium with code '" + code + "' not found!");
		}
		else if (result.size() > 1)
		{
			throw new AmbiguousIdentifierException(
					"Stadium code '" + code + "' is not unique, " + result.size() + " stadiums found!");
		}
		return result.get(0);
	}

	@Required
	public void setStadiumDAO(final StadiumDAO stadiumDao)
	{
		this.stadiumDao = stadiumDao;
	}

}
