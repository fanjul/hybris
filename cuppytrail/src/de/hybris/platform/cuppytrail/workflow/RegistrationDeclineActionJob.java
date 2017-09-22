/**
 *
 */
package de.hybris.platform.cuppytrail.workflow;

import de.hybris.platform.cuppy.model.PlayerModel;
import de.hybris.platform.workflow.model.WorkflowActionModel;
import de.hybris.platform.workflow.model.WorkflowDecisionModel;

import org.apache.log4j.Logger;


/**
 * @author Fernando Bangho
 *
 */
public class RegistrationDeclineActionJob extends AbstractPlayerRegistrationActionJob
{
	private static final Logger LOG = Logger.getLogger(RegistrationDeclineActionJob.class);

	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.platform.workflow.jobs.AutomatedWorkflowTemplateJob#perform(de.hybris.platform.workflow.model.
	 * WorkflowActionModel)
	 */
	@Override
	public WorkflowDecisionModel perform(final WorkflowActionModel action)
	{
		final PlayerModel player = getAttachedPlayer(action);

		LOG.info("Player " + player.getUid() + " declined and will be removed.");

		getModelService().remove(player);

		for (final WorkflowDecisionModel decision : action.getDecisions())
		{
			return decision;
		}
		return null;
	}

}
