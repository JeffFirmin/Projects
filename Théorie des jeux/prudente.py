from scipy.optimize import linprog


class CfgOpt:

    def __init__(self,pierre1,pierre2,emplacement,nbpierre,):
        self.id = CalculS.taskNumber
        CalculS.taskNumber +=1
        self.pierre1 = pierre1
        self.pierre2 = pierre2
        self.emplacement = emplacement
        self.nbpierre = nbpierre
        self.sops = []
        self.gops = 0
        self.hash = 0
        self.g_history = dict()
        self.calculS()

    def calculS(self):
      #  print("test")
      # print(self.pierre2)
       # print(self.emplacement)
        self.hash = "(" + str(self.pierre1) +", " + str(self.pierre2)+ ", "+ str(self.emplacement) +")"; # Ex : (5,5,-1)
        record = self.g_history.get(hash)
        if record is not None:
            self.gops = record.gobs
            self.sops = record.sops
            return
        i = 0
        if self.pierre1 == self.pierre2 and self.pierre1 == self.nbpierre and self.emplacement == 0 :
          #  print("ici")
            self.gops = 0
            while i<self.pierre1 :
                self.sops.append(1.0/self.pierre1)
                i+=1
            self.g_history = {hash: self}
            return

        if self.pierre1 == self.pierre2 and self.pierre1 == 0 :
            if 0 > self.emplacement :
                self.gops = -1
                self.g_history = {hash: self}
                return
            elif 0 < self.emplacement :
                self.gops = -1
                self.g_history = {hash: self}
                return
            else :
                self.gops = 0
                self.g_history = {hash: self}
                return

        if self.pierre1 == 0 :
            if self.pierre2 > self.emplacement:
                self.gops = -1
                self.g_history = {hash: self}
                return
            elif self.pierre2 < self.emplacement:
                self.gops = 1
                self.g_history = {hash: self}
                return
            else :
                self.gops = 0
                self.g_history = {hash: self}
                return

        if self.pierre2 == 0 :
            if self.pierre1 + self.emplacement > 0:
                self.gops = 1
                self.g_history = {hash: self}
                return
            elif self.pierre1 + self.emplacement < 0:
                self.gops = -1
                self.g_history = {hash: self}
                return
            else :
                self.gops = 0
                self.g_history = {hash: self}
                return
        # Methode pour avoir le resultat : Calculer l'equilibre de nash puis resoudre l'equation lineaire
        c = [0,0,0, -1]
        A = [[0,0,0, -1], [-1, 0,0,-1]]
        b = [0,0]
        bounds=(1, self.pierre1)
        res = linprog(c, A, b,None,None,bounds)
        print("Resultat")
        print(res)
        x = res.get('x')
       # print(x[0])
        self.sops.append(int(x[0]))

    def getG(self):
        return self.gops

    def getStones(self):
       # print(self.sops)
       # print(self.gops)
       # print("TEST :")
        max=0
        i=0
     #   print(len(self.sops))
        nb_pierre_envoye=1;
        if(len(self.sops) !=0):
            while i < len(self.sops):

                if(self.sops[i] > max):

                    max = self.sops[i];
                i+=1
            nb_pierre_envoye=self.sops.index(max)+1
        return nb_pierre_envoye


class CalculS :

    taskNumber = 0

    def getStrategy(self,currentStones,jeu,chateau):
        x = currentStones
        t = jeu.troll.emplacement - ((jeu.nb_cases -1)/2)
        if(jeu.chateau1.nom == chateau.nom):
            y = jeu.chateau1.pierre
        else :
            y = jeu.chateau2.pierre
            t=-t
        n= jeu.nb_pierres
        cfg = CfgOpt(x,y,t,n)
        return cfg.getStones()


