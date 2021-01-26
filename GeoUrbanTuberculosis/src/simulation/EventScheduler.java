package simulation;

import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.engine.schedule.ISchedulableAction;
import repast.simphony.engine.schedule.ISchedule;
import repast.simphony.engine.schedule.ScheduleParameters;
import repast.simphony.essentials.RepastEssentials;

public class EventScheduler {

	/**
	 * Instance
	 */
	private static EventScheduler instance;

	/**
	 * Create a new event scheduler
	 */
	private EventScheduler() {
	}

	/**
	 * Get instance
	 */
	public static EventScheduler getInstance() {
		if (instance == null) {
			instance = new EventScheduler();
		}
		return instance;
	}

	/**
	 * Schedule one-time event
	 * 
	 * @param ticksToEvent Ticks to event
	 * @param obj          Object
	 * @param methodName   Method's name
	 * @param methodParams Method's parameters
	 */
	public ISchedulableAction scheduleOneTimeEvent(double ticksToEvent,
			Object obj, String methodName, Object... methodParams) {
		ISchedule schedule = RunEnvironment.getInstance().getCurrentSchedule();
		double currentTick = RepastEssentials.GetTickCount();
		double startTime = currentTick + ticksToEvent;
		ScheduleParameters params = ScheduleParameters.createOneTime(startTime);
		return schedule.schedule(params, obj, methodName, methodParams);
	}

	/**
	 * Schedule recurring event
	 * 
	 * @param ticksToEvent Ticks to event
	 * @param obj          Object
	 * @param tickInterval Tick interval
	 * @param methodName   Method's name
	 * @param methodParams Method's parameters
	 */
	public ISchedulableAction scheduleRecurringEvent(double ticksToEvent,
			Object obj, double tickInterval, String methodName,
			Object... methodParams) {
		ISchedule schedule = RunEnvironment.getInstance().getCurrentSchedule();
		double currentTick = RepastEssentials.GetTickCount();
		double startTime = currentTick + ticksToEvent;
		ScheduleParameters params = ScheduleParameters
				.createRepeating(startTime, tickInterval);
		return schedule.schedule(params, obj, methodName, methodParams);
	}

}