package com.github.albocoder.jnotify;

import java.awt.*;
import javax.swing.*;
import java.awt.geom.RoundRectangle2D;

@SuppressWarnings("serial")
public class Notification extends JPanel{
    // global constants
    public static final String ANIMATION_FADING = "fading";
    public static final String ANIMATION_NONE = "none";
    public static final String BOTTOM_LEFT = "bottomleft";
    public static final String BOTTOM_RIGHT = "bottomright";
    public static final String TOP_LEFT = "topleft";
    public static final String TOP_RIGHT = "topright";
    public static final String TYPE_DANGER = "danger";
    public static final String TYPE_WARNING = "warning";
    public static final String TYPE_SUCCESS = "success";
    public static final int DEFAULT_ORDER = 1;
    // constants
    private final int W = 250;
    private final int H = 60;
    private final int TEXT_MAX_CHAR = 33;
    private final int TITLE_MAX_CHAR = 15;
    // UI related
    private JFrame f;
    private JLabel text;
    private JLabel title;
    private JPanel textholder;
    //properties
    private String type;        //danger,warning,success
    private int order;
    private String position;
    private final int X_PADDING,Y_PADDING;
    private int xPos,yPos;
    
    public Notification(String ty,String ti,String msg){
        this(ty,ti,msg,BOTTOM_RIGHT);
    }
    public Notification(String ty,String ti,String msg,String p){
        this(ty,ti,msg,p,NotificationManager.DEFAULT_PAD,NotificationManager.DEFAULT_PAD);
    }
    public Notification(String ty,String ti,String msg,String p,int xpad,int ypad){
        this(ty,ti,msg,p,xpad,ypad,DEFAULT_ORDER);
    }
    public Notification(String ty,String ti,String msg,String p,int xpad,int ypad,int o) {
        super(true);
        type = ty;
        order = o;
        position = p;
        X_PADDING = xpad;
        Y_PADDING = ypad;
        
        if(ti.length()> TITLE_MAX_CHAR+3)
            ti = ti.substring(0,TITLE_MAX_CHAR-3)+"...";
        if(msg.length()> TEXT_MAX_CHAR+3)
            msg = msg.substring(0,TEXT_MAX_CHAR-3)+"...";
        
        title = new JLabel("<html><body><font size='5'>"+ti+"</font></body></html>");
        text = new JLabel("<html><body><font size='2'>"+msg+"</font></body></html>");
        
        this.setLayout(new BorderLayout());
        add(new JLabel(" "),BorderLayout.WEST);
        add(new JLabel(" "),BorderLayout.EAST);
        textholder = new JPanel();
        textholder.setLayout(new BorderLayout());
        textholder.add(title,BorderLayout.NORTH);
        textholder.add(text,BorderLayout.CENTER);
        add(textholder);
        makeFrame();
        colorize();
    }
   
    // give color according to the notification type
    private void colorize(){
        Color panelBackColor;
        Color titleColor;
        Color textColor;
        if(type.equalsIgnoreCase(TYPE_DANGER)){
            panelBackColor = Color.RED;
            titleColor = Color.YELLOW;
            textColor = Color.YELLOW;
        }
        else if(type.equalsIgnoreCase(TYPE_WARNING)){
            panelBackColor = Color.ORANGE;
            titleColor = Color.BLACK;
            textColor = Color.BLACK;
        }
        else{
            panelBackColor = Color.GREEN;
            titleColor = Color.BLACK;
            textColor = Color.BLACK;
        }
        this.setBackground(panelBackColor);
        textholder.setBackground(panelBackColor);
        text.setForeground(textColor);
        title.setForeground(titleColor);
    }          
            
    // constructor of the frame
    private void makeFrame(){
        f = new JFrame("TranslucentWindow");
        f.setUndecorated( true );
        f.setBackground(new Color(0f, 0f, 0f, 1f / 3f));
        f.setSize(new Dimension(W,H));     // can be made screen dependant
        f.setAlwaysOnTop(true);
        f.setShape(new RoundRectangle2D.Double(0, 0, W, H, 20, 20));
        f.add(this);
        f.setType(javax.swing.JFrame.Type.UTILITY);
        f.setAutoRequestFocus(false);
    }
    
    // taking care of the location in screen
    private void reorder(){
        GraphicsEnvironment ge = 
            GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        Insets insets = Toolkit.getDefaultToolkit().getScreenInsets(gd.getDefaultConfiguration());
        Rectangle screen = gd.getDefaultConfiguration().getBounds();
        Rectangle safeBounds = new Rectangle(screen);
        safeBounds.x += insets.left;
        safeBounds.y += insets.top;
        safeBounds.width -= (insets.left + insets.right);
        safeBounds.height -= (insets.top + insets.bottom);
        if (position.equalsIgnoreCase(BOTTOM_RIGHT))
            reorderBottomRight(safeBounds);
        else if(position.equalsIgnoreCase(BOTTOM_LEFT))
            reorderBottomLeft(safeBounds);
        else if(position.equalsIgnoreCase(TOP_RIGHT))
            reorderTopRight(safeBounds);
        else
            reorderTopLeft(safeBounds);
    }
    private void reorderBottomRight(Rectangle screen){
        xPos = screen.x+screen.width-f.getWidth()-X_PADDING;
        yPos = (screen.y+screen.height)-(f.getHeight()+5)*order-Y_PADDING;
        f.setLocation(xPos,yPos);
    }
    private void reorderTopRight(Rectangle screen){
        xPos = screen.x+screen.width-f.getWidth()-X_PADDING;
        yPos = screen.y+(f.getHeight()+5)*(order-1)+Y_PADDING;
        f.setLocation(xPos,yPos);
    }
    private void reorderBottomLeft(Rectangle screen){
        xPos = screen.x+X_PADDING;
        yPos = (screen.y+screen.height)-(f.getHeight()+5)*order-Y_PADDING;
        f.setLocation(xPos,yPos);
    }
    private void reorderTopLeft(Rectangle screen){
        xPos = screen.x+X_PADDING;
        yPos = screen.y+(f.getHeight()+5)*(order-1)+Y_PADDING; //this is not OK
        f.setLocation(xPos,yPos);
    }
    public void setOrder(int neworder){
        if(neworder == 0)
            neworder = 1;
        order = neworder;
        reorder();
    }
    
    // showing and hiding notification
    public void showUp(String animation) {
        /*
        clip.play();
        */
        reorder();
        f.setResizable(false);
        if(animation.equalsIgnoreCase(ANIMATION_FADING)){
            try{
                fadeIn(f);
            } catch(InterruptedException e){f.setOpacity(0.8f);f.setVisible(true);}
        }
        else{
            f.setOpacity(0.8f);
            f.setVisible(true);
        }
    }
    public void dismiss(String animation){
        if(f == null)
            return;
        if(animation.equalsIgnoreCase(ANIMATION_FADING)){
            try {
                this.fadeOut(f);
            } catch (InterruptedException ex) {f.setVisible(false);}
        }
        else
            f.setVisible(false);
        f = null;
    }
    
    // supported animations
    public void fadeIn(JFrame f) throws InterruptedException{
        f.setOpacity(0.1f);
        f.setVisible(true);
        for (float o = 0.2f;o <= 0.8;o+=0.1f){
            f.setOpacity(o);
            Thread.sleep(100);
        }
        f.revalidate();
    }
    
    public void fadeOut(JFrame f) throws InterruptedException{
        for (float o = 0.7f;o >= 0.1;o-=0.1f){
            f.setOpacity(o);
            Thread.sleep(100);
        }
        f.setVisible(false);
    }
    
    // getters
    public int getxPos(){return xPos;}
    public int getyPos(){return yPos;}
    @Override
    public int getWidth(){return W;}
    @Override
    public int getHeight(){return H;}
}
