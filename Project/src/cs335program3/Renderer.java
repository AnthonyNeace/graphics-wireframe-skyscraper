package cs335program3;

/*
 * @author Anthony Neace for CS 335 October 31, 2012
 * based on NeHe tutorials & in-class OpenGL demos
 */

import com.sun.opengl.util.GLUT;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

class Renderer implements GLEventListener {
    
    private static final float TRAN_DELTA = 0.03f;
    private static final float ROT_DELTA = 1.0f;
    private static final float SCALE_DELTA = 1.0f;
    
    private float rotation_degree = 0.0f;
    private float mouse_rotation_degree = 0.0f;
    private float translation_x = 0.0f;
    private float translation_y = 0.0f;    
    private float scale_factor = SCALE_DELTA;  
    private boolean update = false;
    private boolean resetval = false;
    
    float rtri;
    
    private int width = 600;
    private int height = 600;           
        
    public void Set_zeros() {
        rotation_degree = 0.0f;
        mouse_rotation_degree = 0.0f;
        translation_x = 0.0f;
        translation_y = 0.0f;   
        scale_factor = 1.0f;
        update = true;
    }
    
    //Handles left to right movement
    public void mouseMotion(int initX, int initY, int xVal, int yVal){
        Set_zeros();
        translation_x = -((initX-xVal))*0.01f;
        translation_y = ((initY-yVal))*0.01f;
        System.out.println("Final Translation: "+translation_x+" "+translation_y);
    }
    
    // Handles Y axis rotation
    public void mouseRotation(int origY, int newY){
        Set_zeros();
        mouse_rotation_degree = (origY-newY)*0.01f;
        System.out.println("Rotation: "+mouse_rotation_degree);
    }    
    
    // Handles key movements
    public void Move_up() {
        Set_zeros();
        translation_y = TRAN_DELTA;
    }
    
    public void Move_down() {
        Set_zeros();
        translation_y = -TRAN_DELTA;
    }
    
    public void Move_left() {
        Set_zeros();
        translation_x = -TRAN_DELTA;
    }
    
    public void Move_right() {
        Set_zeros();
        translation_x = TRAN_DELTA;
    }
    
    //left
    public void Rotate_CCW() {
        Set_zeros();
        rotation_degree = ROT_DELTA;
    }
    
    //right
    public void Rotate_CW() {
        Set_zeros();
        rotation_degree = -ROT_DELTA;
    }
    
    public void Reset() {
        scale_factor = SCALE_DELTA;
        Set_zeros();
        resetval = true;
    }    
    
    public void Zoom_In() {
        Set_zeros();
        scale_factor += 0.05f;
    }    
    
    public void Zoom_Out() {
        Set_zeros();
        scale_factor -= 0.05f;
    }    
    
    public void display(GLAutoDrawable gLDrawable) {              
        final GL gl = gLDrawable.getGL();
        gl.glEnable(GL.GL_DEPTH_TEST);

        rtri += 0.6f;     
        
        // Clear the drawing area
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        
        // Determine initial position for this iteration of the drawing on screen
        // If any of the values in the statement below are irrelevant, they
        // will have been set to zero by the set_zeros function
        if (update) {             

            if(resetval){
                gl.glLoadIdentity();
                resetval = false;
            }
            
            gl.glTranslatef(translation_x, translation_y, 0.0f);
            gl.glRotatef(rotation_degree,0.0f,1.0f,0.0f);
            gl.glRotatef(mouse_rotation_degree,1.0f,0.0f,0.0f);
            gl.glScalef(scale_factor,scale_factor,scale_factor);
            
            update = false;
        }             
        
        //Drawing Skyscraper Frame
        gl.glBegin(GL.GL_QUADS);
            //gl.glColor3f(1.0f, 1.0f, 1.0f);    // Set the current drawing color to white
            gl.glColor3f(1.0f, 0.0f, 0.0f); 
            gl.glVertex3f(-0.2f, 0.5f, 0.0f);  // Top Left
            gl.glVertex3f(0.2f, 0.5f, 0.0f);   // Top Right
            gl.glColor3f(1.0f, 1.0f, 0.2f);  
            gl.glVertex3f(0.2f, -0.2f, 0.0f);  // Bottom Right
            gl.glVertex3f(-0.2f, -0.2f, 0.0f); // Bottom Left
        // Done Drawing The Quad
        gl.glEnd();
        
        gl.glBegin(GL.GL_QUADS);
            //gl.glColor3f(1.0f, 1.0f, 1.0f);    // Set the current drawing color to white
            gl.glColor3f(1.0f, 0.0f, 0.0f);
            gl.glVertex3f(-0.2f, 0.5f, -0.4f);  // Top Left
            gl.glVertex3f(0.2f, 0.5f, -0.4f);   // Top Right
            gl.glColor3f(1.0f, 1.0f, 0.2f); 
            gl.glVertex3f(0.2f, -0.2f, -0.4f);  // Bottom Right
            gl.glVertex3f(-0.2f, -0.2f, -0.4f); // Bottom Left
        // Done Drawing The Quad
        gl.glEnd();
        
        gl.glBegin(GL.GL_QUADS);
            gl.glColor3f(1.0f, 1.0f, 1.0f);    // Set the current drawing color to white
            gl.glColor3f(1.0f, 0.0f, 0.0f);
            gl.glVertex3f(0.2f, 0.5f, -0.4f);  // Top Left
            gl.glVertex3f(0.2f, 0.5f, -0.0f);   // Top Right
            gl.glColor3f(1.0f, 1.0f, 0.2f); 
            gl.glVertex3f(0.2f, -0.2f, -0.0f);  // Bottom Right
            gl.glVertex3f(0.2f, -0.2f, -0.4f); // Bottom Left
        // Done Drawing The Quad
        gl.glEnd();  
        
        gl.glBegin(GL.GL_QUADS);
            gl.glColor3f(1.0f, 1.0f, 1.0f);    // Set the current drawing color to white
            gl.glColor3f(1.0f, 0.0f, 0.0f);
            gl.glVertex3f(-0.2f, 0.5f, -0.4f);  // Top Left
            gl.glVertex3f(-0.2f, 0.5f, -0.0f);   // Top Right
            gl.glColor3f(1.0f, 1.0f, 0.2f); 
            gl.glVertex3f(-0.2f, -0.2f, -0.0f);  // Bottom Right
            gl.glVertex3f(-0.2f, -0.2f, -0.4f); // Bottom Left
        // Done Drawing The Quad
        gl.glEnd();   
        
        //Roof Detailing
        gl.glBegin(GL.GL_QUADS);
            gl.glColor3f(1.0f, 0.0f, 0.0f);    //red
            gl.glVertex3f(0.2f, 0.49f, -0.4f);  // Top Left
            gl.glVertex3f(0.2f, 0.49f, -0.0f);   // Top Right
            gl.glVertex3f(-0.2f, 0.49f, -0.0f);  // Bottom Right
            gl.glVertex3f(-0.2f, 0.49f, -0.4f); // Bottom Left
        // Done Drawing The Quad
        gl.glEnd();   
        
        gl.glBegin(GL.GL_QUADS);
            gl.glColor3f(1.0f, 1.0f, 0.2f);     // yellow
            gl.glVertex3f(0.2f, -0.1f, -0.4f);  // Top Left
            gl.glVertex3f(0.2f, -0.1f, -0.0f);   // Top Right
            gl.glVertex3f(-0.2f,-0.1f, -0.0f);  // Bottom Right
            gl.glVertex3f(-0.2f,-0.1f, -0.4f); // Bottom Left
        gl.glEnd();
        
        gl.glBegin(GL.GL_QUADS);
            gl.glColor3f(1.0f, 1.0f, 0.2f);       // yellow
            gl.glVertex3f(0.2f, -0.11f, -0.4f);  // Top Left
            gl.glVertex3f(0.2f, -0.11f, -0.0f);   // Top Right
            gl.glVertex3f(-0.2f,-0.11f, -0.0f);  // Bottom Right
            gl.glVertex3f(-0.2f,-0.11f, -0.4f); // Bottom Left
        gl.glEnd();   
        
        gl.glBegin(GL.GL_QUADS);
            gl.glColor3f(1.0f, 1.0f, 0.2f);       // yellow
            gl.glVertex3f(0.2f, -0.09f, -0.4f);  // Top Left
            gl.glVertex3f(0.2f, -0.09f, -0.0f);   // Top Right
            gl.glVertex3f(-0.2f,-0.09f, -0.0f);  // Bottom Right
            gl.glVertex3f(-0.2f,-0.09f, -0.4f); // Bottom Left
        gl.glEnd();         
        // End Skyscraper Frame
        
        //Doors
        gl.glBegin(GL.GL_QUADS);
            gl.glColor3f(1.0f, 1.0f, 1.0f);    // Set the current drawing color to white
            gl.glVertex3f(-0.04f, -0.12f, 0.0f);  // Top Left
            gl.glVertex3f(0.04f, -0.12f, 0.0f);   // Top Right
            gl.glVertex3f(0.04f, -0.2f, 0.0f);  // Bottom Right
            gl.glVertex3f(-0.04f, -0.2f, 0.0f); // Bottom Left
        // Done Drawing The Quad
        gl.glEnd();        
        
        //Ground Plane
        gl.glBegin(GL.GL_QUADS);
            gl.glColor3f(0.0f, 1.0f, 0.2f);    // Set the current drawing color to green
            gl.glVertex3f(0.5f, -0.2f, -0.5f);  // Top Left
            gl.glVertex3f(0.5f, -0.2f, 0.5f);   // Top Right
            gl.glVertex3f(-0.5f, -0.2f, 0.5f);  // Bottom Right
            gl.glVertex3f(-0.5f, -0.2f, -0.5f); // Bottom Left
        // Done Drawing The Quad
        gl.glEnd();        
        
        // Rotating Glass Door
        gl.glPushMatrix();
        gl.glRotatef(rtri,0.0f,1.0f,0.0f);
        gl.glBegin(GL.GL_QUADS);
            gl.glColor3f(0.5f, 0.5f, 1.0f);    // Set the current drawing color to light blue
            gl.glVertex3f(-0.04f, -0.12f, 0.0f);  // Top Left
            gl.glVertex3f(0.00f, -0.12f, 0.0f);   // Top Right
            gl.glVertex3f(0.00f, -0.2f, 0.0f);  // Bottom Right
            gl.glVertex3f(-0.04f, -0.2f, 0.0f); // Bottom Left
        gl.glEnd();  
        gl.glPopMatrix();  
        
        // Rotating Glass Door
        gl.glPushMatrix();
        gl.glRotatef(rtri,0.0f,1.0f,0.0f);
        gl.glBegin(GL.GL_QUADS);
            gl.glColor3f(0.5f, 0.5f, 1.0f);    // Set the current drawing color to light blue
            gl.glVertex3f(0.04f, -0.12f, 0.0f);  // Top Left
            gl.glVertex3f(0.00f, -0.12f, 0.0f);   // Top Right
            gl.glVertex3f(0.00f, -0.2f, 0.0f);  // Bottom Right
            gl.glVertex3f(0.04f, -0.2f, 0.0f); // Bottom Left
        gl.glEnd();  
        gl.glPopMatrix();          
        
        // Rotating Glass Door
        gl.glPushMatrix();
        gl.glRotatef(rtri,0.0f,1.0f,0.0f);
        gl.glBegin(GL.GL_QUADS);
            gl.glColor3f(0.5f, 0.5f, 1.0f);    // Set the current drawing color to light blue
            gl.glVertex3f(0.00f, -0.12f, -0.04f);  // Top Left
            gl.glVertex3f(0.00f, -0.12f, 0.0f);   // Top Right
            gl.glVertex3f(0.00f, -0.2f, 0.0f);  // Bottom Right
            gl.glVertex3f(0.00f, -0.2f, -0.04f); // Bottom Left
        gl.glEnd();  
        gl.glPopMatrix();         
        
        // Rotating Glass Door
        gl.glPushMatrix();
        gl.glRotatef(rtri,0.0f,1.0f,0.0f);
        gl.glBegin(GL.GL_QUADS);
            gl.glColor3f(0.5f, 0.5f, 1.0f);    // Set the current drawing color to light blue
            gl.glVertex3f(0.00f, -0.12f, 0.04f);  // Top Left
            gl.glVertex3f(0.00f, -0.12f, 0.0f);   // Top Right
            gl.glVertex3f(0.00f, -0.2f, 0.0f);  // Bottom Right
            gl.glVertex3f(0.00f, -0.2f, 0.04f); // Bottom Left
        gl.glEnd();  
        gl.glPopMatrix();            
        
        //Road
        gl.glBegin(GL.GL_QUADS);
            gl.glColor3f(1.0f, 1.0f, 1.0f);    // Set the current drawing color to white
            gl.glVertex3f(0.5f, -0.2f, 0.45f);  // Top Left
            gl.glVertex3f(0.5f, -0.2f, 0.30f);   // Top Right
            gl.glVertex3f(-0.5f, -0.2f, 0.30f);  // Bottom Right
            gl.glVertex3f(-0.5f, -0.2f, 0.45f); // Bottom Left
        // Done Drawing The Quad
        gl.glEnd();   
        
        //Plaza in front of Skyscraper
        gl.glBegin(GL.GL_QUADS);
            gl.glColor3f(1.0f, 1.0f, 1.0f);    // Set the current drawing color to white
            gl.glVertex3f(0.2f, -0.2f, 0.15f);  // Top Left
            gl.glVertex3f(0.2f, -0.2f, 0.0f);   // Top Right
            gl.glVertex3f(-0.2f, -0.2f, 0.0f);  // Bottom Right
            gl.glVertex3f(-0.2f, -0.2f, 0.15f); // Bottom Left
        // Done Drawing The Quad
        gl.glEnd();   
        
        //Sidewalk detailing
        gl.glBegin(GL.GL_QUADS);
            gl.glColor3f(1.0f, 1.0f, 1.0f);    // Set the current drawing color to white
            gl.glVertex3f(0.5f, -0.19f, 0.25f);  // Top Left
            gl.glVertex3f(0.5f, -0.2f, 0.25f);   // Top Right
            gl.glVertex3f(0.2f, -0.2f, 0.25f);  // Bottom Right
            gl.glVertex3f(0.2f, -0.19f, 0.25f); // Bottom Left
        gl.glEnd();
        
        //Sidewalk detailing
        gl.glBegin(GL.GL_QUADS);
            gl.glColor3f(1.0f, 1.0f, 1.0f);    // Set the current drawing color to white
            gl.glVertex3f(-0.2f, -0.19f, 0.25f);  // Top Left
            gl.glVertex3f(-0.2f, -0.2f, 0.25f);   // Top Right
            gl.glVertex3f(-0.5f, -0.2f, 0.25f);  // Bottom Right
            gl.glVertex3f(-0.5f, -0.19f, 0.25f); // Bottom Left
        gl.glEnd();        
        
        //Sidewalk detailing
        gl.glBegin(GL.GL_QUADS);
            gl.glColor3f(1.0f, 1.0f, 1.0f);    // Set the current drawing color to white
            gl.glVertex3f(0.5f, -0.19f, 0.30f);  // Top Left
            gl.glVertex3f(0.5f, -0.2f, 0.30f);   // Top Right
            gl.glVertex3f(-0.5f, -0.2f, 0.30f);  // Bottom Right
            gl.glVertex3f(-0.5f, -0.19f, 0.30f); // Bottom Left
        gl.glEnd();                
        
        //Sidewalk detailing
        gl.glBegin(GL.GL_QUADS);
            gl.glColor3f(1.0f, 1.0f, 1.0f);    // Set the current drawing color to white
            gl.glVertex3f(0.5f, -0.19f, 0.45f);  // Top Left
            gl.glVertex3f(0.5f, -0.2f, 0.45f);   // Top Right
            gl.glVertex3f(-0.5f, -0.2f, 0.45f);  // Bottom Right
            gl.glVertex3f(-0.5f, -0.19f, 0.45f); // Bottom Left
        gl.glEnd();           
        
        //Sidewalk detailing
        gl.glBegin(GL.GL_QUADS);
            gl.glColor3f(1.0f, 1.0f, 1.0f);    // Set the current drawing color to white
            gl.glVertex3f(0.5f, -0.19f, 0.5f);  // Top Left
            gl.glVertex3f(0.5f, -0.2f, 0.5f);   // Top Right
            gl.glVertex3f(-0.5f, -0.2f, 0.5f);  // Bottom Right
            gl.glVertex3f(-0.5f, -0.19f, 0.5f); // Bottom Left
        gl.glEnd();      
        
        // Triangle - Sidewalk Detailing
        gl.glBegin(GL.GL_TRIANGLES);
            gl.glVertex3f(0.2f, -0.19f, 0.25f);   // Top
            gl.glVertex3f(0.2f, -0.2f, 0.25f); // Bottom Left
            gl.glVertex3f(0.2f, -0.2f, 0.15f);  // Bottom Right
        // Finished Drawing The Triangle
        gl.glEnd();
        
        // Triangle - Sidewalk Detailing
        gl.glBegin(GL.GL_TRIANGLES);
            gl.glVertex3f(-0.2f, -0.19f, 0.25f);   // Top
            gl.glVertex3f(-0.2f, -0.2f, 0.25f); // Bottom Left
            gl.glVertex3f(-0.2f, -0.2f, 0.15f);  // Bottom Right
        // Finished Drawing The Triangle
        gl.glEnd();        
        
        //Sidewalk
        for(int i = 0; i < 20; i++){
            //Sidewalk Surface Close to Building
            gl.glBegin(GL.GL_QUADS);
                gl.glColor3f(1.0f, 1.0f, 1.0f);    // Set the current drawing color to white
                gl.glVertex3f(0.5f-(0.05f*i), -0.19f, 0.30f);  // Top Left
                gl.glVertex3f(0.5f-(0.05f*i), -0.19f, 0.25f);   // Top Right
                gl.glVertex3f(0.45f-(0.05f*i), -0.19f, 0.25f);  // Bottom Right
                gl.glVertex3f(0.45f-(0.05f*i), -0.19f, 0.3f); // Bottom Left
            gl.glEnd();    
            
            //Sidewalk Surface Away from Building
            gl.glBegin(GL.GL_QUADS);
                gl.glColor3f(1.0f, 1.0f, 1.0f);    // Set the current drawing color to white
                gl.glVertex3f(0.5f-(0.05f*i), -0.19f, 0.5f);  // Top Left
                gl.glVertex3f(0.5f-(0.05f*i), -0.19f, 0.45f);   // Top Right
                gl.glVertex3f(0.45f-(0.05f*i), -0.19f, 0.45f);  // Bottom Right
                gl.glVertex3f(0.45f-(0.05f*i), -0.19f, 0.5f); // Bottom Left
            gl.glEnd(); 
            
            //Road Strips
            gl.glBegin(GL.GL_QUADS);
                gl.glColor3f(1.0f, 1.0f, 0.2f);    // Set the current drawing color to yellow
                gl.glVertex3f(0.50f-(0.05f*i), -0.2f, 0.3775f);  // Top Left
                gl.glVertex3f(0.50f-(0.05f*i), -0.2f, 0.3725f);   // Top Right
                gl.glVertex3f(0.47f-(0.05f*i), -0.2f, 0.3725f);  // Bottom Right
                gl.glVertex3f(0.47f-(0.05f*i), -0.2f, 0.3775f); // Bottom Left
            gl.glEnd(); 
        }
        
        //Next Set of Sidewalk Panels
        //Sidewalk
        for(int i = 0; i < 8; i++){
            //Sidewalk Surface Close to Building
            gl.glBegin(GL.GL_QUADS);
                gl.glColor3f(1.0f, 1.0f, 1.0f);    // Set the current drawing color to white
                gl.glVertex3f(0.2f-(0.05f*i), -0.19f, 0.25f);  // Top Left
                gl.glVertex3f(0.2f-(0.05f*i), -0.195f, 0.20f);   // Top Right
                gl.glVertex3f(0.15f-(0.05f*i), -0.195f, 0.20f);  // Bottom Right
                gl.glVertex3f(0.15f-(0.05f*i), -0.19f, 0.25f); // Bottom Left
            gl.glEnd();    
            
            //Sidewalk Ramping to Plaza
            gl.glBegin(GL.GL_QUADS);
                gl.glColor3f(1.0f, 1.0f, 1.0f);    // Set the current drawing color to white
                gl.glVertex3f(0.2f-(0.05f*i), -0.195f, 0.20f);  // Top Left
                gl.glVertex3f(0.2f-(0.05f*i), -0.20f, 0.15f);   // Top Right
                gl.glVertex3f(0.15f-(0.05f*i), -0.20f, 0.15f);  // Bottom Right
                gl.glVertex3f(0.15f-(0.05f*i), -0.195f, 0.20f); // Bottom Left
            gl.glEnd();              
        }        
        
        //Drawing Skyscraper Windows
        gl.glColor3f(0.5f, 0.5f, 1.0f);    // Set the current drawing color to light blue
        for(int i =0;i < 6; i++){
            gl.glColor3f(0.20f+(0.1f*i), 0.20f+(0.1f*i), 1.0f);
            for(int wall1=0; wall1 < 4; wall1++){
                gl.glBegin(GL.GL_QUADS);
                    gl.glVertex3f(-0.18f+(0.10f*wall1), 0.48f-(0.10f*i), 0.0f);  // Top Left
                    gl.glVertex3f(-0.12f+(0.10f*wall1), 0.48f-(0.10f*i), 0.0f);   // Top Right
                    gl.glVertex3f(-0.12f+(0.10f*wall1), 0.42f-(0.10f*i), 0.0f);  // Bottom Right
                    gl.glVertex3f(-0.18f+(0.10f*wall1), 0.42f-(0.10f*i), 0.0f); // Bottom Left
                // Done Drawing The Quad
                gl.glEnd();
            }
            for(int wall2=0; wall2 < 4; wall2++){
                gl.glBegin(GL.GL_QUADS);
                    gl.glVertex3f(-0.18f+(0.10f*wall2), 0.48f-(0.10f*i), -0.4f);  // Top Left
                    gl.glVertex3f(-0.12f+(0.10f*wall2), 0.48f-(0.10f*i), -0.4f);   // Top Right
                    gl.glVertex3f(-0.12f+(0.10f*wall2), 0.42f-(0.10f*i), -0.4f);  // Bottom Right
                    gl.glVertex3f(-0.18f+(0.10f*wall2), 0.42f-(0.10f*i), -0.4f); // Bottom Left
                // Done Drawing The Quad
                gl.glEnd();
            }          
            for(int wall3=0; wall3 < 4; wall3++){
                gl.glBegin(GL.GL_QUADS);
                    gl.glVertex3f(0.2f, 0.48f-(0.10f*i), -0.38f+(0.10f*wall3));  // Top Left
                    gl.glVertex3f(0.2f, 0.48f-(0.10f*i), -0.32f+(0.10f*wall3));   // Top Right
                    gl.glVertex3f(0.2f, 0.42f-(0.10f*i), -0.32f+(0.10f*wall3));  // Bottom Right
                    gl.glVertex3f(0.2f, 0.42f-(0.10f*i), -0.38f+(0.10f*wall3)); // Bottom Left
                // Done Drawing The Quad
                gl.glEnd();
            }              
            for(int wall3=0; wall3 < 4; wall3++){
                gl.glBegin(GL.GL_QUADS);
                    gl.glVertex3f(-0.2f, 0.48f-(0.10f*i), -0.38f+(0.10f*wall3));  // Top Left
                    gl.glVertex3f(-0.2f, 0.48f-(0.10f*i), -0.32f+(0.10f*wall3));   // Top Right
                    gl.glVertex3f(-0.2f, 0.42f-(0.10f*i), -0.32f+(0.10f*wall3));  // Bottom Right
                    gl.glVertex3f(-0.2f, 0.42f-(0.10f*i), -0.38f+(0.10f*wall3)); // Bottom Left
                // Done Drawing The Quad
                gl.glEnd();
            }                           
        }                
    }

    public void displayChanged(GLAutoDrawable gLDrawable, boolean modeChanged, boolean deviceChanged) {
    }

    public void init(GLAutoDrawable gLDrawable) {
        GL gl = gLDrawable.getGL();
        GLU glu = new GLU();
        GLUT glut = new GLUT();
        
        gl.glLoadIdentity();
        
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);    // Black transparent Background
        gl.glPolygonMode (GL.GL_FRONT_AND_BACK, GL.GL_LINE);
    }

    public void reshape(GLAutoDrawable gLDrawable, int x, int y, int width, int height) {        
        this.width = width;
        this.height = height;
    }
}
