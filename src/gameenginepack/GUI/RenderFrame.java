package gameenginepack.GUI;

import gameenginepack.Instances.*;
import gameenginepack.RenderTypes;

import javax.swing.*;

public class RenderFrame extends JPanel {

    private RenderTypes renderType;
    private Camera camera;


    public RenderFrame(RenderTypes renderType,Camera camera) {
        this.renderType = renderType;
        this.camera = camera;
    }

    public void setRenderType(RenderTypes renderType) {
        this.renderType = renderType;
    }

}
