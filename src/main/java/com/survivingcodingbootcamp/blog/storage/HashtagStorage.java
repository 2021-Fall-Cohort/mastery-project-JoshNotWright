package com.survivingcodingbootcamp.blog.storage;

import com.survivingcodingbootcamp.blog.model.Hashtag;

public interface HashtagStorage {
    Iterable<Hashtag> retrieveAllHashtags();

    Hashtag retrieveHashtagByName(String name);

    void save(Hashtag hashtagToAdd);
}
