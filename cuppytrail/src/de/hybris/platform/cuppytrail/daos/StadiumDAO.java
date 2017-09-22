/**
 *
 */
package de.hybris.platform.cuppytrail.daos;

import de.hybris.platform.cuppytrail.model.StadiumModel;

import java.util.List;


/**
 * @author Fernando Bangho
 *
 */
public interface StadiumDAO
{

	public List<StadiumModel> findStadiums();

	public List<StadiumModel> findStadiumsByCode(String code);
}
