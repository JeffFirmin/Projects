<?php

namespace OC\PlatformBundle\Entity;

use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\ORM\Mapping as ORM;

use Gedmo\Mapping\Annotation as Gedmo;
use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Component\Validator\Context\ExecutionContextInterface;
use Symfony\Bridge\Doctrine\Validator\Constraints\UniqueEntity;
use OC\PlatformBundle\Validator\Antiflood;
use OC\UserBundle\Entity\User;
use DoctrineExtensions\Taggable\Taggable;


/**
 * @ORM\Table(name="oc_annonce")
 * @ORM\Entity(repositoryClass="OC\PlatformBundle\Repository\AnnonceRepository")
 * @UniqueEntity(fields="title", message="Une annonce existe déjà avec ce titre.")
 * @ORM\HasLifecycleCallbacks()
 */
class Annonce implements Taggable
{
  /**
   * @var int
   *
   * @ORM\Column(name="id", type="integer")
   * @ORM\Id
   * @ORM\GeneratedValue(strategy="AUTO")
   */
  private $id;

  /**
   * @var \DateTime
   *
   * @ORM\Column(name="date", type="datetime")
   * @Assert\DateTime()
   */
  private $date;

  /**
   * @var string
   *
   * Et pour être logique, il faudrait aussi mettre la colonne titre en Unique pour Doctrine :
   * @ORM\Column(name="title", type="string", length=255, unique=true)
   * @Assert\Length(min=5)
   */
  private $title;

  /**
   * @var string
   *
   * @ORM\Column(name="url", type="string", length=255)
   * @Assert\url
   */
  private $url;

  /**
   * @var string
   *
   * @ORM\Column(name="content", type="string", length=255)
   * @Antiflood()
   * @Assert\NotBlank()
   */
  private $content;

  /**
   * @ORM\Column(name="published", type="boolean")
   */
  private $published = true;

  /**
   * @ORM\OneToOne(targetEntity="OC\PlatformBundle\Entity\Image", cascade={"persist", "remove"})
   * @Assert\Valid()
   */
  private $image;


  /**
   * @ORM\OneToOne(targetEntity="OC\PlatformBundle\Entity\Thread")
   * @Assert\Valid()
   */
  private $thread;
  

  /**
   * @ORM\ManyToOne(targetEntity="OC\PlatformBundle\Entity\Rubrique", cascade={"persist"})
   */
  private $rubrique;
  
   /**
   * @ORM\ManyToMany(targetEntity="OC\PlatformBundle\Entity\Sousrubrique", cascade={"persist"})
   * @ORM\JoinTable(name="oc_annonce_sous_rubrique")
   */
   private $Sousrubriques;
  

  /**
   * @ORM\ManyToOne(targetEntity="OC\UserBundle\Entity\User", cascade={"persist"})
   * @ORM\JoinColumn(nullable=false)
   */
  private $user; 


  /**
   * @ORM\Column(name="updated_at", type="datetime", nullable=true)
   */
  private $updatedAt;

  /**
   * @ORM\Column(name="nb_applications", type="integer")
   */
  private $nbApplications = 0;

  //variable pour le bundle tagbundle
   private $tags;
  

   /**
   * @param ArrayCollection $tags
   * @return $this
   */
   public function setTags(ArrayCollection $tags)
   {
     $this->tags = $tags;

     return $this;
    }
   

    public function getTags()
    {
        $this->tags = $this->tags ?: new ArrayCollection();

        return $this->tags;
    }

    public function getTaggableType()
    {
        return 'annonce_tag';
    }

    public function getTaggableId()
    {
        return $this->getId();
    }


  public function __construct()
  {
    $this->date         = new \Datetime();
    $this->rubrique   = new ArrayCollection();
    
  }

  /**
   * @ORM\PreUpdate
   */
  public function updateDate()
  {
    $this->setUpdatedAt(new \Datetime());
  }

  public function increaseApplication()
  {
    $this->nbApplications++;
  }

  public function decreaseApplication()
  {
    $this->nbApplications--;
  }

  /**
   * @return int
   */
  public function getId()
  {
    return $this->id;
  }

  /**
   * @param \DateTime $date
   */
  public function setDate($date)
  {
    $this->date = $date;
  }

  /**
   * @return \DateTime
   */
  public function getDate()
  {
    return $this->date;
  }

  /**
   * @param string $title
   */
  public function setTitle($title)
  {
    $this->title = $title;
  }

  /**
   * @return string
   */
  public function getTitle()
  {
    return $this->title;
  }
   

  
  /**
   * @param string $content
   */
  public function setContent($content)
  {
    $this->content = $content;
  }

  /**
   * @return string
   */
  public function getContent()
  {
    return $this->content;
  }

  /**
   * @param bool $published
   */
  public function setPublished($published)
  {
    $this->published = $published;
  }

  /**
   * @return bool
   */
  public function getPublished()
  {
    return $this->published;
  }

  public function setImage(Image $image = null)
  {
    $this->image = $image;
  }

  public function getImage()
  {
    return $this->image;
  }
  

   /**
     * Set rubrique
     *
     * @param \OC\PlatformBundle\Entity\Rubrique $rubrique
     *
     * @return Annonce
     */
    public function setRubrique(\OC\PlatformBundle\Entity\Rubrique $rubrique = null)
    {
        $this->rubrique = $rubrique;

        return $this;
    }

    /**
     * Get rubrique
     *
     * @return \OC\PlatformBundle\Entity\Rubrique
     */
    public function getRubrique()
    {
        return $this->rubrique;
    }


    /**
     * Set url
     *
     * @param string $url
     *
     * @return Annonce
     */
    public function setUrl($url)
    {
        $this->url = $url;

        return $this;
    }

    /**
     * Get url
     *
     * @return string
     */
    public function getUrl()
    {
        return $this->url;
    }

    /**
     * Set user
     *
     * @param \OC\UserBundle\Entity\User $user
     *
     * @return Annonce
     */
    public function setUser(\OC\UserBundle\Entity\User $user)
    {
        $this->user = $user;

        return $this;
    }

    /**
     * Get user
     *
     * @return \OC\UserBundle\Entity\User
     */
    public function getUser()
    {
        return $this->user;
    }
    


    /**
     * Add sousrubrique
     *
     * @param \OC\PlatformBundle\Entity\Sousrubrique $sousrubrique
     *
     * @return Annonce
     */
    public function addSousrubrique(\OC\PlatformBundle\Entity\Sousrubrique $sousrubrique)
    {
        $this->Sousrubriques[] = $sousrubrique;

        return $this;
    }

    /**
     * Remove sousrubrique
     *
     * @param \OC\PlatformBundle\Entity\Sousrubrique $sousrubrique
     */
    public function removeSousrubrique(\OC\PlatformBundle\Entity\Sousrubrique $sousrubrique)
    {
        $this->Sousrubriques->removeElement($sousrubrique);
    }

    /**
     * Get sousrubriques
     *
     * @return \Doctrine\Common\Collections\Collection
     */
    public function getSousrubriques()
    {
        return $this->Sousrubriques;
    }

  /**
   * @param \DateTime $updatedAt
   */
  public function setUpdatedAt(\Datetime $updatedAt = null)
  {
      $this->updatedAt = $updatedAt;
  }

  /**
   * @return \DateTime
   */
  public function getUpdatedAt()
  {
      return $this->updatedAt;
  }

  /**
   * @param integer $nbApplications
   */
  public function setNbApplications($nbApplications)
  {
      $this->nbApplications = $nbApplications;
  }

  /**
   * @return integer
   */
  public function getNbApplications()
  {
      return $this->nbApplications;
  }

  
   /**
   * @Assert\IsTrue()
   */
  public function isTitle()
  {
    //return false;
    return true;
  }

  /**
   * @Assert\Callback
   */
  public function isContentValid(ExecutionContextInterface $context)
  {
    $forbiddenWords = array('démotivation', 'abandon');

    // On vérifie que le contenu ne contient pas l'un des mots
    if (preg_match('#'.implode('|', $forbiddenWords).'#', $this->getContent())) {
      // La règle est violée, on définit l'erreur
      $context
        ->buildViolation('Contenu invalide car il contient un mot interdit.') // message
        ->atPath('content')                                                   // attribut de l'objet qui est violé
        ->addViolation() // ceci déclenche l'erreur, ne l'oubliez pas
        
      /*$this->context
              ->buildViolation($constraint->message)
              //->atPath('content') 
              ->setParameters(array('%string%' => $value))
              ->addViolation()
              */
      ;
     }
  }


    /**
     * Set thread
     *
     * @param \OC\PlatformBundle\Entity\Thread $thread
     *
     * @return Annonce
     */
    public function setThread(\OC\PlatformBundle\Entity\Thread $thread = null)
    {
        $this->thread = $thread;

        return $this;
    }

    /**
     * Get thread
     *
     * @return \OC\PlatformBundle\Entity\Thread
     */
    public function getThread()
    {
        return $this->thread;
    }
}
