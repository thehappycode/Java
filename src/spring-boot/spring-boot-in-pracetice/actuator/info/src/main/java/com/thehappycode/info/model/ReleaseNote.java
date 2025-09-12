package com.thehappycode.info.model;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ReleaseNote {
    private String version;
    private LocalDate releaseDate;
    private String commitTag;
    private Set<ReleaseItem> newReleases;
    private Set<ReleaseItem> bugFixes;

    @Override
    public boolean equals(Object other){
        if(this == other){
            return true;
        }

        if(!(other instanceof ReleaseNote)){
            return false;
        }

        ReleaseNote that = (ReleaseNote)other;

        return Objects.equals(getVersion(), that.getVersion());
    }

    @Override
    public int hashCode(){
        return Objects.hash(getVersion());
    }
}


