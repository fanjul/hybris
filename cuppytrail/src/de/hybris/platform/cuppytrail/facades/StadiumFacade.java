/**
 *
 */
package de.hybris.platform.cuppytrail.facades;

import de.hybris.platform.cuppytrail.data.StadiumData;

import java.util.List;


/**
 * @author Fernando Bangho
 *
 */
public interface StadiumFacade
{
	public StadiumData getStadium(String name);

	public List<StadiumData> getStadiums();
}
