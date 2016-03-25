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
	private boolean isAcquiring;
	
	public CameraSystem(String name) {
		super();
		this.name = name;
		
		Image frameTmp = null;
		int sessionTmp = 0;
		try {
			frameTmp = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
			sessionTmp = NIVision.IMAQdxOpenCamera(name, 
	        		NIVision.IMAQdxCameraControlMode.CameraControlModeController);
	        NIVision.IMAQdxConfigureGrab(sessionTmp);
		} catch (Exception e) {
			System.err.println("Failed to initialize camera");
			System.err.println(e.getStackTrace());
			frameTmp = null;
			sessionTmp = 0;
		}
		this.frame = frameTmp;
		this.session = sessionTmp;
		this.isAcquiring = false;
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
	
	protected void drawRectangle() {
        /**
         * grab an image, draw the circle, and provide it for the camera server
         * which will in turn send it to the dashboard.
         */
        NIVision.Rect rect = new NIVision.Rect(10, 10, 100, 100);


        NIVision.imaqDrawShapeOnImage(frame, frame, rect,
                DrawMode.DRAW_VALUE, ShapeMode.SHAPE_OVAL, 0.0f);
        
	}

	public void grabImage() {

		if (frame != null && !isAcquiring) {
			isAcquiring = true;
			try {
		        NIVision.IMAQdxStartAcquisition(session);
		        NIVision.IMAQdxGrab(session, frame, 1);
		        CameraServer.getInstance().setImage(frame);
		        NIVision.IMAQdxStopAcquisition(session);		
			} catch (Exception e) {
				System.err.println("Failed to get image from camera");
				System.err.println(e.getStackTrace());
			} finally {
		        /*
		         * JVM can run out of memory and crash. The following is supposed to help.
		         * see http://www.chiefdelphi.com/forums/showthread.php?t=145192&highlight=camera+java+usb
		         */
		        //frame.free();
			}
			isAcquiring = false;
		}

	}

}
