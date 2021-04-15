<?php

namespace OC\PlatformBundle\Entity;

use \FPN\TagBundle\Entity\Tag as BaseTag;
use Doctrine\ORM\Mapping as ORM;

/**
 * OC\PlatformBundle\Entity\Tag
 *
 * @ORM\Table()
 * @ORM\Entity
 */
class Tag extends BaseTag
{
    /**
     * @var integer $id
     *
     * @ORM\Column(name="id", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    protected $id;

    /**
     * @ORM\OneToMany(targetEntity="Tagging", mappedBy="tag", fetch="EAGER")
     **/
    protected $tagging;

    /**
     * Add tagging
     *
     * @param \OC\PlatformBundle\Entity\Tagging $tagging
     *
     * @return Tag
     */
    public function addTagging(\OC\PlatformBundle\Entity\Tagging $tagging)
    {
        $this->tagging[] = $tagging;

        return $this;
    }

    /**
     * Remove tagging
     *
     * @param \OC\PlatformBundle\Entity\Tagging $tagging
     */
    public function removeTagging(\OC\PlatformBundle\Entity\Tagging $tagging)
    {
        $this->tagging->removeElement($tagging);
    }

    /**
     * Get tagging
     *
     * @return \Doctrine\Common\Collections\Collection
     */
    public function getTagging()
    {
        return $this->tagging;
    }
}
