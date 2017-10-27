package com.github.albocoder.jnotify;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

public class NotificationManager {
    // global constants
    public static final int DEFAULT_PAD = 10;
    public static final int NO_KILL=-1;
    public static final int DEFAULT_DURATION = 10;
    // properties
    private final Vector<Notification> notificationList;
    private final MouseListener ml;
    private final String position;
    private final int XPAD,YPAD;
    private final String animation;
    
    public NotificationManager(){
        this(Notification.BOTTOM_RIGHT,Notification.ANIMATION_NONE,DEFAULT_PAD,DEFAULT_PAD);
    }
    public NotificationManager(String p){
        this(p,Notification.ANIMATION_NONE,DEFAULT_PAD,DEFAULT_PAD);
    }
    public NotificationManager(String p,String a){
        this(p,a,DEFAULT_PAD,DEFAULT_PAD);
    }
    public NotificationManager(String p,String a,int xpad,int ypad){
        ml = new MouseListener();
        XPAD = xpad;
        YPAD = ypad;
        notificationList = new Vector<Notification>();
        position = p;
        animation = a;
    }
    
    public void notify(String type,String title, String msg,String a,int duration){
        Notification n = new Notification(type,title,msg,position,XPAD,YPAD,notificationList.size()+1);
        n.addMouseListener(ml);
        if(duration != NO_KILL)
            new Thread(new AutoKiller(n,duration,a)).start();
        notificationList.add(n);
        n.showUp(a);
    }
    public void notify(String type,String title, String msg,int duration){
        notify(type,title,msg,animation,duration);
    }
    public void notify(String type,String title, String msg){
        notify(type,title,msg,DEFAULT_DURATION);
    }
    public void notifyNoAutokill(String type,String title, String msg,String a){
        notify(type,title,msg,a,NO_KILL);
    }
    public void notifyNoAutokill(String type,String title, String msg){
        notify(type,title,msg,animation,NO_KILL);
    }
    public void killNotification(Notification n,String a){
        n.dismiss(a);
        if(notificationList == null)    
            return;
        int myplace = notificationList.indexOf(n);
        if(myplace < notificationList.size()-1){
            for(int i = myplace+1; i < notificationList.size(); i++){
                notificationList.elementAt(i).setOrder(i);
            }
        }
        notificationList.remove(n);
    }
    
    // private classes
    private class MouseListener extends MouseAdapter{
        @Override
        public void mouseReleased(MouseEvent e){
            Notification n = (Notification) e.getComponent();
            killNotification(n,animation);
        }
    }
    private class AutoKiller implements Runnable {
        private final Notification n;
        private final int timer;
        private final String anim;
        public AutoKiller(Notification n,int t,String a){
            this.n = n;
            timer = t;
            anim = a;
        }    
        @Override
        public void run() {
            try {
                Thread.sleep(timer*1000);
            } catch (InterruptedException ex) {}
            killNotification(n,anim);
        }
    }
}
