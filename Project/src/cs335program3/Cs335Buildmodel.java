package cs335program3;

import common.GLDisplay;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.SwingUtilities;

/**
 * @author Anthony Neace for CS 335 October 31, 2012
 * based on NeHe tutorials & in-class OpenGL demos
 */
public class Cs335Buildmodel {
    
    //Initialize Vars
    static int xVal;
    static int yVal;
    static int initX;
    static int initY;
    static int oldX = -10000;
    static int oldY = -10000;
    static int defaultY = 0;
    
    
    //Program Control Flow
    public static void main(String[] args) {
        GLDisplay neheGLDisplay = GLDisplay.createGLDisplay("CS335 Program 3 - Anthony Neace - City Skyscraper", 600, 600);
        Renderer renderer = new Renderer();
        InputHandler inputHandler = new InputHandler(renderer, neheGLDisplay);
        //Add listeners
        neheGLDisplay.addGLEventListener(renderer);
        neheGLDisplay.addKeyListener(inputHandler);
        mouseHandler(neheGLDisplay,renderer);
        mouseMotionHandler(neheGLDisplay,renderer);
        neheGLDisplay.start();
    }
    
    //Handles Mouse Dragged Events
    private static void mouseMotionHandler(GLDisplay neheGLDisplay, final Renderer renderer){
            neheGLDisplay.addMouseMotionListener(new MouseMotionAdapter(){
            
            @Override
            public void mouseMoved(MouseEvent e){
                //System.out.println("Oy!");
            }
            
            @Override
            public void mouseDragged(MouseEvent e){
                if(SwingUtilities.isRightMouseButton(e)){
                    System.out.println("Right drag dawg");
                    Point newPoint = MouseInfo.getPointerInfo().getLocation();
                    yVal =  (int) newPoint.getY();
                    renderer.mouseRotation(initY,yVal);
                }
                else{
                    //Get Point on Screen
                    Point newPoint = MouseInfo.getPointerInfo().getLocation();
                    xVal =  (int) newPoint.getX();
                    yVal =  (int) newPoint.getY();
                    if(oldX == -10000){
                        oldX = xVal;
                        oldY = yVal; 
                        renderer.mouseMotion(initX, initY, xVal, yVal);
                    }
                    else{
                        renderer.mouseMotion(oldX, oldY, xVal, yVal);
                        oldX = xVal;
                        oldY = yVal; 
                    }
                }
            }
            
            
        });
    }    
    
    //Handles Mouse Clicked Events
    private static void mouseHandler(GLDisplay neheGLDisplay, final Renderer renderer){
            neheGLDisplay.addMouseListener(new MouseAdapter(){

            boolean isPressed;

            @Override
            public void mousePressed(MouseEvent e){
                if(SwingUtilities.isRightMouseButton(e)){
                    System.out.println("Right click dawg");
                    Point newPoint = MouseInfo.getPointerInfo().getLocation();
                    initY =  (int) newPoint.getY();
                }
                else{
                
                    isPressed = true;
                    //Get Point on Screen
                    Point newPoint = MouseInfo.getPointerInfo().getLocation();
                    initX =  (int) newPoint.getX();
                    initY =  (int) newPoint.getY();  
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {            
                isPressed = false;
                oldX = -10000;
                oldY = -10000; 
            }

            @Override
            public void mouseExited(MouseEvent e) {
                isPressed = false;
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                isPressed = true;
            }                    
        });
    }
}
    