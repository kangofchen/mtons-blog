/*
+--------------------------------------------------------------------------
|   Mblog [#RELEASE_VERSION#]
|   ========================================
|   Copyright (c) 2014, 2015 mtons. All Rights Reserved
|   http://www.mtons.com
|
+---------------------------------------------------------------------------
*/
package mblog.core.planet.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import mblog.core.data.Tag;
import mblog.core.persist.service.TagService;
import mblog.core.planet.TagPlanet;

/**
 * @author langhsu
 * 
 */
@Service
public class TagPlanetImpl implements TagPlanet {
	@Autowired
	private TagService tagService;

	@Override
	@Cacheable(value = "tagsCaches", key = "'top_tags_' + #maxResults + '_' + #loadPost")
	public List<Tag> topTags(int maxResults, boolean loadPost) {
		return tagService.topTags(maxResults, loadPost);
	}

	@Override
	@CacheEvict(value = "tagsCaches", allEntries = true)
	public void delete(long id) {
		tagService.delete(id);
	}

	@Override
	@CacheEvict(value = "tagsCaches", allEntries = true)
	public boolean cacheFlush() {
		return Boolean.TRUE;
	}
}
