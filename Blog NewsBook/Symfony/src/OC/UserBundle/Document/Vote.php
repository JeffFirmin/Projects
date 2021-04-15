<?php
// src/OC/PlatformBundle/Document/Vote.php

namespace OC\PlatformBundle\Document;

use Doctrine\ODM\MongoDB\Mapping\Annotations as MongoDB;
use FOS\CommentBundle\Document\Vote as BaseVote;

/**
 * @MongoDB\Document
 * @MongoDB\ChangeTrackingPolicy("DEFERRED_EXPLICIT")
 */
class Vote extends BaseVote
{
    /**
     * @MongoDB\Id
     */
    protected $id;

    /**
     * Comment of this vote
     *
     * @var Comment
     * @MongoDB\ReferenceOne(targetDocument="OC\PlatformBundle\Document\Comment")
     */
    protected $comment;
}