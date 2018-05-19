package com.rolartech.startedservice;

import android.app.Activity;
import android.content.Context;
import android.hardware.Camera;
import android.widget.ImageButton;


/**
 * Created by SulemanKhan on 27/09/2017.
 */

public class FlashLight {
    private Camera camera;
    private boolean isFlashOn;
    Camera.Parameters params;



    // getting camera parameters
    public void getCamera() {
        if (camera == null) {
            try {
                camera = Camera.open();
                params = camera.getParameters();
            } catch (RuntimeException e) {

            }
        }
    }

    public void offCamera() {
        if(camera!=null){
            camera.stopPreview();
            camera.setPreviewCallback(null);

            camera.release();
            camera = null;
        }
    }
    /*
    * Turning On flash
    */
    public void turnOnFlash() {
        if (!isFlashOn) {
            if (camera == null || params == null) {
                return;
            }
            params = camera.getParameters();
            params.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            camera.setParameters(params);
            camera.startPreview();
            isFlashOn = true;
        }
    }

    /*
 * Turning Off flash
 */
    public void turnOffFlash() {
        if (isFlashOn) {
            if (camera == null || params == null) {
                return;
            }
            params = camera.getParameters();
            params.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            camera.setParameters(params);
            camera.stopPreview();
            isFlashOn = false;
        }
    }

    public boolean isFlashLightOn(){
        return isFlashOn;
    }
}

