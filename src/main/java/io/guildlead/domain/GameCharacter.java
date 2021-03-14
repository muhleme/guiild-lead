package io.guildlead.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import static io.guildlead.domain.GameCharacterType.ALT_CHARACTER;
import static io.guildlead.domain.GameCharacterType.MAIN_CHARACTER;

@Getter
@EqualsAndHashCode
public class GameCharacter {
    private final Member owner;
    private final String name;
    private final String server;
    GameCharacterType characterType = MAIN_CHARACTER;

    GameCharacter(String name, String server, Member owner) {
        this.name = name;
        this.server = server;
        this.owner = owner;
    }

    static GameCharacter newAltCharacter(String name, String server, Member owner) {
        GameCharacter altCharacter = new GameCharacter(name, server, owner);
        altCharacter.characterType = ALT_CHARACTER;
        return altCharacter;
    }


}

