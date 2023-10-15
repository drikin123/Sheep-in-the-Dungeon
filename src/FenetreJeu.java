import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;


public class FenetreJeu extends JPanel implements KeyListener{
    private Terrain terrain;
    private int tailleCase = 40;
    private int hauteur, largeur;
    private JFrame frame;
    static int dj=0;
    static int dk=0;
    static int mp=0;
    static int pm=0;
    static int om=0;


    public FenetreJeu(Terrain t) {
        this.hauteur = t.getHauteur();
        this.largeur = t.getLargeur();
        this.terrain = t;


        setBackground(new java.awt.Color(150, 200, 100));
        //"./imagesipo/grass.jpg"
        setPreferredSize(new Dimension(largeur * tailleCase, hauteur * tailleCase));

        JFrame frame = new JFrame("Donjon");
        this.frame = frame;



        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
        frame.pack();
        frame.setVisible(true);
        frame.addKeyListener(this);
        frame.setFocusable(true);
        frame.requestFocusInWindow();
    }



    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {this.terrain.droite();dj=0;}
        if(e.getKeyCode() == KeyEvent.VK_DOWN) {this.terrain.bas();dj=1;}
        if(e.getKeyCode() == KeyEvent.VK_UP) {this.terrain.haut();dj=2;}
        if(e.getKeyCode() == KeyEvent.VK_LEFT) {this.terrain.gauche();dj=3;}
        if(e.getKeyCode() == KeyEvent.VK_SPACE) {Jeu.berg=true;}
        if(e.getKeyChar() == 'd') {this.terrain.droitek();dk=0;}
        if(e.getKeyChar() == 's') {this.terrain.bask();dk=1;}
        if(e.getKeyChar() == 'z') {this.terrain.hautk();dk=2;}
        if(e.getKeyChar() == 'q') {this.terrain.gauchek();dk=3;}
        if(e.getKeyChar() == 'k') {Jeu.kil=true;}
        if(e.getKeyChar() == 'l') {Jeu.kil=false;}
        if(e.getKeyChar() == 'm') {
            if(mp<2) {
                mp++;
            }
            else {mp=0;}
        }
        if(e.getKeyChar() == 'p') {
            if(pm<2) {
                pm++;
            }
            else {pm=0;}
        }
        if(e.getKeyChar() == 'o') {
            if(om<2) {
                om++;
            }
            else {om=0;}
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }



    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int h=0; h<this.hauteur; h++) {
            for (int l=0; l<this.largeur; l++) {
                if(mp==0) {
                    ImageIcon img = new ImageIcon("./imagesipo/grass.jpg");
                    g.drawImage(img.getImage(), l*tailleCase, h*tailleCase, tailleCase, tailleCase, null);
                }
                else if(mp==1) {
                    ImageIcon img = new ImageIcon("./imagesipo/sec.png");
                    g.drawImage(img.getImage(), l*tailleCase, h*tailleCase, tailleCase, tailleCase, null);
                }
                else if(mp==2) {
                    ImageIcon img = new ImageIcon("./imagesipo/glace.png");
                    g.drawImage(img.getImage(), l*tailleCase, h*tailleCase, tailleCase, tailleCase, null);
                }
            }
        }

        for (int h=0; h<this.hauteur; h++) {
            for (int l=0; l<this.largeur; l++) {
                if(this.terrain.getCarte(h, l) instanceof CaseIntraversable && this.terrain.getCarte(h, l) instanceof Sortie==false && this.terrain.getCarte(h, l).getContenu() instanceof Entite==false) {
                    if(om==0) {
                        ImageIcon img = new ImageIcon("./imagesipo/game.jpg");
                        g.drawImage(img.getImage(), l*tailleCase, h*tailleCase, tailleCase, tailleCase, null);
                    }
                    else if(om==1) {
                        ImageIcon img = new ImageIcon("./imagesipo/game2.jpg");
                        g.drawImage(img.getImage(), l*tailleCase, h*tailleCase, tailleCase, tailleCase, null);
                    }
                    else if(om==2) {
                        ImageIcon img = new ImageIcon("./imagesipo/game3.jpg");
                        g.drawImage(img.getImage(), l*tailleCase, h*tailleCase, tailleCase, tailleCase, null);
                    }


                }

                else if(this.terrain.getCarte(h, l).getContenu() instanceof Personnage) {
                    EntiteMobile a=(EntiteMobile) this.terrain.getCarte(h, l).getContenu();
                    if(a.getDir()==Direction.nord) {
                        ImageIcon img = new ImageIcon("./imagesipo/sheeph.png");
                        g.drawImage(img.getImage(), l*tailleCase, h*tailleCase, tailleCase, tailleCase, null);
                    }
                    else if(a.getDir()==Direction.sud) {
                        ImageIcon img = new ImageIcon("./imagesipo/sheepb.png");
                        g.drawImage(img.getImage(), l*tailleCase, h*tailleCase, tailleCase, tailleCase, null);
                    }
                    else if(a.getDir()==Direction.est) {
                        ImageIcon img = new ImageIcon("./imagesipo/sheepd.png");
                        g.drawImage(img.getImage(), l*tailleCase, h*tailleCase, tailleCase, tailleCase, null);
                    }
                    else if(a.getDir()==Direction.ouest) {
                        ImageIcon img = new ImageIcon("./imagesipo/sheepg.png");
                        g.drawImage(img.getImage(), l*tailleCase, h*tailleCase, tailleCase, tailleCase, null);
                    }
                }
                else if(this.terrain.getCarte(h, l).getContenu() instanceof Monstre) {
                    EntiteMobile a=(EntiteMobile) this.terrain.getCarte(h, l).getContenu();
                    if(a.getDir()==Direction.nord) {
                        ImageIcon img = new ImageIcon("./imagesipo/wolfh.png");
                        g.drawImage(img.getImage(), l*tailleCase, h*tailleCase, tailleCase, tailleCase, null);
                    }
                    else if(a.getDir()==Direction.sud) {
                        ImageIcon img = new ImageIcon("./imagesipo/wolfb.png");
                        g.drawImage(img.getImage(), l*tailleCase, h*tailleCase, tailleCase, tailleCase, null);
                    }
                    else if(a.getDir()==Direction.est) {
                        ImageIcon img = new ImageIcon("./imagesipo/wolfd.png");
                        g.drawImage(img.getImage(), l*tailleCase, h*tailleCase, tailleCase, tailleCase, null);
                    }
                    else if(a.getDir()==Direction.ouest) {
                        ImageIcon img = new ImageIcon("./imagesipo/wolfg.png");
                        g.drawImage(img.getImage(), l*tailleCase, h*tailleCase, tailleCase, tailleCase, null);
                    }

                }

                else if(this.terrain.getCarte(h, l).getContenu() instanceof Obstacle) {
                    if(pm==0) {
                        ImageIcon img = new ImageIcon("./imagesipo/legume.png");
                        g.drawImage(img.getImage(), l*tailleCase, h*tailleCase, tailleCase, tailleCase, null);
                    }
                    else if(pm==1) {
                        ImageIcon img = new ImageIcon("./imagesipo/legume2.png");
                        g.drawImage(img.getImage(), l*tailleCase, h*tailleCase, tailleCase, tailleCase, null);
                    }
                    else if(pm==2) {
                        ImageIcon img = new ImageIcon("./imagesipo/legume3.png");
                        g.drawImage(img.getImage(), l*tailleCase, h*tailleCase, tailleCase, tailleCase, null);
                    }
                }
                else if(this.terrain.getCarte(h, l).getContenu() instanceof Joueur) {
                    if(dj==0) {
                        ImageIcon img = new ImageIcon("./imagesipo/farmer2d.png");
                        g.drawImage(img.getImage(), l*tailleCase, h*tailleCase, tailleCase, tailleCase, null);
                    }
                    else if(dj==1) {
                        ImageIcon img = new ImageIcon("./imagesipo/farmer2b.png");
                        g.drawImage(img.getImage(), l*tailleCase, h*tailleCase, tailleCase, tailleCase, null);
                    }
                    else if(dj==2) {
                        ImageIcon img = new ImageIcon("./imagesipo/farmer2h.png");
                        g.drawImage(img.getImage(), l*tailleCase, h*tailleCase, tailleCase, tailleCase, null);
                    }
                    else if(dj==3) {
                        ImageIcon img = new ImageIcon("./imagesipo/farmer2g.png");
                        g.drawImage(img.getImage(), l*tailleCase, h*tailleCase, tailleCase, tailleCase, null);
                    }


                }
                else if(this.terrain.getCarte(h, l).getContenu() instanceof Killer) {
                    if(dk==0) {
                        ImageIcon img = new ImageIcon("./imagesipo/killerd.png");
                        g.drawImage(img.getImage(), l*tailleCase, h*tailleCase, tailleCase, tailleCase, null);
                    }
                    else if(dk==1) {
                        ImageIcon img = new ImageIcon("./imagesipo/killerb.png");
                        g.drawImage(img.getImage(), l*tailleCase, h*tailleCase, tailleCase, tailleCase, null);
                    }
                    else if(dk==2) {
                        ImageIcon img = new ImageIcon("./imagesipo/killerh.png");
                        g.drawImage(img.getImage(), l*tailleCase, h*tailleCase, tailleCase, tailleCase, null);
                    }
                    else if(dk==3) {
                        ImageIcon img = new ImageIcon("./imagesipo/killerg.png");
                        g.drawImage(img.getImage(), l*tailleCase, h*tailleCase, tailleCase, tailleCase, null);
                    }


                }
                else if(this.terrain.getCarte(h, l) instanceof Sortie) {
                    ImageIcon img = new ImageIcon("./imagesipo/house2.gif");
                    g.drawImage(img.getImage(), l*tailleCase, h*tailleCase, tailleCase, tailleCase, null);
                }
                else if(this.terrain.getCarte(h, l).getContenu() instanceof Fire) {
                    ImageIcon img = new ImageIcon("./imagesipo/fire2.gif");
                    g.drawImage(img.getImage(), l*tailleCase, h*tailleCase, tailleCase, tailleCase, null);
                }
                else {
                    if(mp==0) {
                        ImageIcon img = new ImageIcon("./imagesipo/grass.jpg");
                        g.drawImage(img.getImage(), l*tailleCase, h*tailleCase, tailleCase, tailleCase, null);
                    }
                    else if(mp==1) {
                        ImageIcon img = new ImageIcon("./imagesipo/sec.png");
                        g.drawImage(img.getImage(), l*tailleCase, h*tailleCase, tailleCase, tailleCase, null);
                    }
                    else if(mp==2) {
                        ImageIcon img = new ImageIcon("./imagesipo/glace.png");
                        g.drawImage(img.getImage(), l*tailleCase, h*tailleCase, tailleCase, tailleCase, null);
                    }
                }
            }
        }

    }

    public void ecranFinal(int n) {
        frame.remove(this);
        JLabel label = new JLabel("Score " + n);
        label.setFont(new Font("Verdana", 1, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setSize(this.getSize());
        frame.getContentPane().add(label);
        frame.repaint();
    }



}