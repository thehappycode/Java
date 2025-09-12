package com.thehappycode.info;

import java.time.LocalDate;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.thehappycode.info.model.ReleaseItem;
import com.thehappycode.info.model.ReleaseNote;

@SpringBootApplication
public class InfoApplication {

	public static void main(String[] args) {
		SpringApplication.run(InfoApplication.class, args);
	}

    @Bean(name = "releaseNotes")
    public Collection<ReleaseNote> loadReleaseNotes(){
        Set<ReleaseNote> releaseNotes = new LinkedHashSet<>();
        ReleaseNote releaseNote1 = ReleaseNote.builder()
            .version("v1.2.1")
            .releaseDate(LocalDate.of(2025, 9, 11))
            .commitTag("8iw9sw")
            .bugFixes(Set.of(
                getReleaseItem("SDVF-123", "The name SDVF-123"),
                getReleaseItem("SDVF-456", "The name SDVF-456")
            ))
            .build();

        releaseNotes.addAll(Set.of(releaseNote1));

        return releaseNotes;
    }

    private ReleaseItem getReleaseItem(String itemId, String itemDescription){
        return ReleaseItem
            .builder()
            .itemId(itemId)
            .itemDescription(itemDescription)
            .build();
    }
}
