<?php
// src/OC/PlatformBundle/Entity/Thread.php

namespace  OC\PlatformBundle\Entity;

use Doctrine\ORM\Mapping as ORM;
use FOS\CommentBundle\Entity\Thread as BaseThread;

/**
 * @ORM\Entity
 * @ORM\ChangeTrackingPolicy("DEFERRED_EXPLICIT")
 */
class Thread extends BaseThread
{
    /**
     * @var string $id
     *
     * @ORM\Id
     * @ORM\Column(type="string")
     */
    protected $id;


  /**
   * @ORM\OneToOne(targetEntity="OC\PlatformBundle\Entity\Annonce")
   */
  private $annonce;

    /**
     * Set annonce
     *
     * @param \OC\PlatformBundle\Entity\Annonce $annonce
     *
     * @return Thread
     */
    public function setAnnonce(\OC\PlatformBundle\Entity\Annonce $annonce = null)
    {
        $this->annonce = $annonce;

        return $this;
    }

    /**
     * Get annonce
     *
     * @return \OC\PlatformBundle\Entity\Annonce
     */
    public function getAnnonce()
    {
        return $this->annonce;
    }

   /* public function getAnnonceName()
    {
        return $this->getAnnonce();
    }
    */
}
