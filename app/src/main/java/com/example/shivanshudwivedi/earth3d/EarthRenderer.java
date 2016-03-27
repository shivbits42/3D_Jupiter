package com.example.shivanshudwivedi.earth3d;

import android.content.Context;
import android.view.MotionEvent;
import android.widget.TextView;
import android.widget.Toast;

import org.rajawali3d.lights.DirectionalLight;
import org.rajawali3d.materials.Material;
import org.rajawali3d.materials.methods.DiffuseMethod;
import org.rajawali3d.materials.textures.ATexture;
import org.rajawali3d.materials.textures.Texture;
import org.rajawali3d.math.vector.Vector3;
import org.rajawali3d.primitives.Sphere;
import org.rajawali3d.renderer.RajawaliRenderer;

/**
 * Created by Shivanshu Dwivedi on 27-03-2016.
 */
public class EarthRenderer extends RajawaliRenderer {

    public Context context;
    private DirectionalLight directionalLight;
    private Sphere sphere;

    public EarthRenderer(Context context) {
        super(context);
        this.context=context;
        setFrameRate(50);
    }

    @Override
    protected void initScene() {

        directionalLight=new DirectionalLight(1f ,0.5f,-1.5f);
        directionalLight.setColor(.5f, .2f, .1f);
        directionalLight.setPower(2);
        getCurrentScene().addLight(directionalLight);

        Material material = new Material();
        material.enableLighting(true);
        material.setDiffuseMethod(new DiffuseMethod.Lambert());
        material.setColor(0);

        Texture PlanetTexture=new Texture("Jupiter",R.drawable.jupiter_nasa);

        try{
            material.addTexture(PlanetTexture);

        }catch (ATexture.TextureException error){

            Toast.makeText(context,"Texture Error",Toast.LENGTH_SHORT).show();

        };


        sphere = new Sphere(1, 24, 24);
        sphere.setMaterial(material);
        getCurrentScene().addChild(sphere);
        getCurrentCamera().setZ(4.2f);

    }

    @Override
    public void onOffsetsChanged(float xOffset, float yOffset, float xOffsetStep, float yOffsetStep, int xPixelOffset, int yPixelOffset) {

    }

    @Override
    public void onTouchEvent(MotionEvent event) {

    }

    @Override
    protected void onRender(long ellapsedRealtime, double deltaTime) {
        super.onRender(ellapsedRealtime, deltaTime);
        sphere.rotate(Vector3.Axis.Y,1.0);
    }
}
