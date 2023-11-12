package io.github.jubadeveloper.several.repository;

import io.github.jubadeveloper.core.domain.Channel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChannelRepository extends JpaRepository<Channel, Long> { }
