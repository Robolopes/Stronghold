package org.usfirst.frc.team2339.robot.subsystems;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.DrawMode;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ShapeMode;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;

public class CameraSystem extends Subsystem {
	private final String name;
	private final int session;
	private final Image frame;
	
	public CameraSystem(String name) {
		super();
		this.name = name;
		
		this.frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
        this.session = NIVision.IMAQdxOpenCamera(name, 
        		NIVision.IMAQdxCameraControlMode.CameraControlModeController);
        NIVision.IMAQdxConfigureGrab(this.session);
	}

	public String getName() {
		return name;
	}

	public int getSession() {
		return session;
	}

	public Image getFrame() {
		return frame;
	}
	
	@Override
	protected void initDefaultCommand() {
	}

	public void grabImage() {
		
        NIVision.IMAQdxStartAcquisition(session);

        /**
         * grab an image, draw the circle, and provide it for the camera server
         * which will in turn send it to the dashboard.
         */
        NIVision.Rect rect = new NIVision.Rect(10, 10, 100, 100);


        NIVision.IMAQdxGrab(session, frame, 1);
        NIVision.imaqDrawShapeOnImage(frame, frame, rect,
                DrawMode.DRAW_VALUE, ShapeMode.SHAPE_OVAL, 0.0f);
        
        CameraServer.getInstance().setImage(frame);

        NIVision.IMAQdxStopAcquisition(session);		
	}

}
