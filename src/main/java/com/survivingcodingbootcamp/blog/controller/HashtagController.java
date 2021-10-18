package com.survivingcodingbootcamp.blog.controller;

import com.survivingcodingbootcamp.blog.model.Hashtag;
import com.survivingcodingbootcamp.blog.model.Post;
import com.survivingcodingbootcamp.blog.storage.HashtagStorage;
import com.survivingcodingbootcamp.blog.storage.PostStorage;
import com.survivingcodingbootcamp.blog.storage.PostStorageJpaImpl;
import com.survivingcodingbootcamp.blog.storage.repository.HashtagRepository;
import org.dom4j.rule.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/hashtags")
public class HashtagController {
    private HashtagStorage hashtagStorage;
    private PostStorage postStorage;
    private HashtagRepository hashtagRepo;

    public HashtagController(HashtagStorage hashtagStorage, PostStorage postStorage) {
        this.hashtagStorage = hashtagStorage;
        this.postStorage = postStorage;
    }

    @GetMapping("")
    public String displayAllHashtags(Model model) {
        model.addAttribute("hashtags", hashtagStorage.retrieveAllHashtags());

        return "all-hashtags-template";
    }

    @GetMapping("/{name}")
    public String displaySingleHashtag(@PathVariable String name, Model model) {
        model.addAttribute("hashtag", hashtagStorage.retrieveHashtagByName(name));

        return "single-hashtag-template";
    }

    @PostMapping("/add")
    public String addHashtag(@RequestParam String name, @RequestParam long id) {
        Post postToUpdate = postStorage.retrievePostById(id);

        if (hashtagStorage.retrieveHashtagByName(name) == null) {
            hashtagStorage.save(new Hashtag(name));
        }

        postToUpdate.addHashtag(hashtagStorage.retrieveHashtagByName(name));
        postStorage.save(postToUpdate);

        return "redirect:/hashtags/" + name;
    }
}
