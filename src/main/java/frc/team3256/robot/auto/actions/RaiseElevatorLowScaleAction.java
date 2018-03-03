package frc.team3256.robot.auto.actions;

import frc.team3256.robot.subsystems.Elevator;

public class RaiseElevatorLowScaleAction extends RunOnceAction{
    @Override
    public void runOnce() {
        Elevator.getInstance().setWantedState(Elevator.WantedState.WANTS_TO_LOW_SCALE_POS);
    }
}
