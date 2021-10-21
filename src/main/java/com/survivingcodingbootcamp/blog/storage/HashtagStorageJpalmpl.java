package com.survivingcodingbootcamp.blog.storage;

import com.survivingcodingbootcamp.blog.model.Hashtag;
import com.survivingcodingbootcamp.blog.storage.repository.HashtagRepository;
import org.springframework.stereotype.Service;

@Service
public class HashtagStorageJpalmpl implements HashtagStorage{
    private HashtagRepository hashtagRepo;

    public HashtagStorageJpalmpl(HashtagRepository hashtagRepo) {
        this.hashtagRepo = hashtagRepo;
    }


    @Override
    public Iterable<Hashtag> retrieveAllHashtags() {
        return hashtagRepo.findAll();
    }

    @Override
    public Hashtag retrieveHashtagByName(String name) {
        return hashtagRepo.findByNameIgnoreCase(name);
    }

    @Override
    public void save(Hashtag hashtagToAdd) {
        hashtagRepo.save(hashtagToAdd);
    }
}
