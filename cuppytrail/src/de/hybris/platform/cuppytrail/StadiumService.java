/**
 *
 */
package de.hybris.platform.cuppytrail;

import de.hybris.platform.cuppytrail.model.StadiumModel;

import java.util.List;


/**
 * @author Fernando Bangho
 *
 */
public interface StadiumService
{

	public List<StadiumModel> getStadiums();

	public StadiumModel getStadiumForCode(String code);

}
