package gameenginepack.GUI;

import gameenginepack.Instances.Camera2D;
import gameenginepack.RenderTypes;

import javax.swing.*;

public class RenderFrame extends JPanel {

    private RenderTypes renderType;
    private Camera2D camera;


    public RenderFrame(RenderTypes renderType,Camera2D camera) {
        this.renderType = renderType;
        this.camera = camera;
    }

    public void setRenderType(RenderTypes renderType) {
        this.renderType = renderType;
    }

}
