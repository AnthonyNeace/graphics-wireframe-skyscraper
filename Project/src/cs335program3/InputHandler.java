package cs335program3;

import common.GLDisplay;
import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

class InputHandler extends KeyAdapter {
    private Renderer renderer;

    public InputHandler(Renderer renderer, GLDisplay glDisplay) {
        this.renderer = renderer;        
        glDisplay.registerKeyStrokeForHelp(KeyStroke.getKeyStroke(KeyEvent.VK_PAGE_UP, 0), "Rotate left in the X direction");
        glDisplay.registerKeyStrokeForHelp(KeyStroke.getKeyStroke(KeyEvent.VK_PAGE_DOWN, 0), "Rotate right in the Y Direction");
        glDisplay.registerKeyStrokeForHelp(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "Move down");
        glDisplay.registerKeyStrokeForHelp(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "Move up");
        glDisplay.registerKeyStrokeForHelp(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "Move left");
        glDisplay.registerKeyStrokeForHelp(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "Move right");
        glDisplay.registerMouseEventForHelp(MouseEvent.MOUSE_DRAGGED, MouseEvent.BUTTON1_DOWN_MASK, "Move in X or Y directions");
        glDisplay.registerMouseEventForHelp(MouseEvent.MOUSE_DRAGGED, MouseEvent.BUTTON3_DOWN_MASK, "Rotate in the Y Direction");
        glDisplay.registerKeyStrokeForHelp(KeyStroke.getKeyStroke(KeyEvent.VK_R, 0), "Reset");
        
    }

    public void keyPressed(KeyEvent e) {
        processKeyEvent(e, true);
    }

    public void keyReleased(KeyEvent e) {
        processKeyEvent(e, false);        
    }

    private void processKeyEvent(KeyEvent e, boolean pressed) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_PAGE_UP:
                renderer.Rotate_CCW();
                break;
            case KeyEvent.VK_PAGE_DOWN:
                renderer.Rotate_CW();
                break;
            case KeyEvent.VK_UP:
                renderer.Move_up();
                break;
            case KeyEvent.VK_DOWN:
                renderer.Move_down();
                break;
            case KeyEvent.VK_RIGHT:
                renderer.Move_right();
                break;
            case KeyEvent.VK_LEFT:
                renderer.Move_left();
                break;                
            case KeyEvent.VK_R:
                renderer.Reset();
                break;                
            case KeyEvent.VK_I:
                renderer.Zoom_In();
                break;         
            case KeyEvent.VK_O:
                renderer.Zoom_Out();
                break;                                     
        }        
    }
    
}
