#include "wavefront.h"
#include "texture.h"
#include "orbiter.h"
#include "draw.h"        
#include "app_camera.h"       

#include <fstream>
#include <iostream>
#include <string>

class TP : public AppCamera
{
public:
    // constructeur : donner les dimensions de l'image, et eventuellement la version d'openGL.
    TP( ) : AppCamera(1024, 780) {}
    
    // creation des objets de l'application
    int init( )
    {
        char buffer[256];
        char buff[2];
        /*for (int i = 0; i < 10; i++)
        {
            strcpy(buffer,"data/rivers/river");
            sprintf(buff,"%d",i+1);
            strcat(buffer,buff);
            strcat(buffer,".obj");
            m_river[i] = read_mesh(buffer);
        }*/
        m_river[0] = read_mesh("data/rivers/river1.obj");
        m_river[1] = read_mesh("data/rivers/river2.obj");
        m_river[2] = read_mesh("data/rivers/river3.obj");
        m_river[3] = read_mesh("data/rivers/river4.obj");
        m_river[4] = read_mesh("data/rivers/river5.obj");
        m_river[5] = read_mesh("data/rivers/river6.obj");
        m_river[6] = read_mesh("data/rivers/river7.obj");
        m_river[7] = read_mesh("data/rivers/river8.obj");
        m_river[8] = read_mesh("data/rivers/river9.obj");
        m_river[9] = read_mesh("data/rivers/river10.obj");
        a = 0;
        m_objet= m_river[0];
        Point pmin, pmax;
        m_objet.bounds(pmin, pmax);
        camera().lookat(pmin, pmax);

        /* si l'objet est gros, il faut regler la camera pour l'observer entierement :
            // recuperer les points extremes de l'objet (son englobant)
            Point pmin, pmax;
            m_objet.bounds(pmin, pmax);
            
            // parametrer la camera
            camera().lookat(pmin, pmax);
        */
        
        m_texture= read_texture(0, "data/debug2x2red.png");

        // etat openGL par defaut
        glClearColor(0.2f, 0.2f, 0.2f, 1.f);        // couleur par defaut de la fenetre
        
        glClearDepth(1.f);                          // profondeur par defaut
        glDepthFunc(GL_LESS);                       // ztest, conserver l'intersection la plus proche de la camera
        glEnable(GL_DEPTH_TEST);                    // activer le ztest

        return 0;   // ras, pas d'erreur
    }

    int update (const float time, const float delta)
    {
        if (a != (int)time/100)
        {
            m_objet= m_river[(int)(time/100)%10];
            a = (int)time/100;
        }
        return 0;
    }
    
    // destruction des objets de l'application
    int quit( )
    {
        m_objet.release();
        glDeleteTextures(1, &m_texture);
        
        return 0;
    }
    
    // dessiner une nouvelle image
    int render( )
    {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        draw(m_objet, Identity(), camera(), m_texture);
        
        return 1;
    }

protected:
    Mesh m_objet;
    Mesh m_river [10];
    GLuint m_texture;
    int count = 0;
    int a;
};


int main( int argc, char **argv )
{
    // il ne reste plus qu'a creer un objet application et la lancer 
    TP tp;
    tp.run();

    return 0;
}
