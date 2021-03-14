package io.guildlead.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

import static io.guildlead.domain.GameCharacterType.ALT_CHARACTER;
import static io.guildlead.domain.GameCharacterType.MAIN_CHARACTER;

@Getter
public class Member {
    private final String nickName;
    private final Set<GameCharacter> gameCharacters = new HashSet<>();
    @Setter
    private MemberStatus memberStatus = MemberStatus.MEMBER;

    public Member(String nickName, String mainCharacterName, String mainCharacterServer) {
        GameCharacter mainCharacter = new GameCharacter(mainCharacterName, mainCharacterServer, this);
        this.gameCharacters.add(mainCharacter);
        this.nickName = nickName;
    }

    public void makeNewMainCharacter(String nameNewMainCharacter) {
        gameCharacters.stream()
                .filter(character -> nameNewMainCharacter.equals(character.getName()))
                .findFirst()
                .ifPresentOrElse(newMainCharacter -> newMainCharacter.characterType = MAIN_CHARACTER,
                        () -> {
                            throw new RuntimeException("Character not found");
                        });
        gameCharacters.stream()
                .filter(character -> MAIN_CHARACTER.equals(character.getCharacterType()))
                .findFirst()
                .ifPresent(character -> character.characterType = ALT_CHARACTER);

    }

    public void addAltCharacter(String altCharacterName, String altCharacterServer) {
        GameCharacter newAltCharacter = GameCharacter.newAltCharacter(altCharacterName, altCharacterServer, this);
        gameCharacters.add(newAltCharacter);
    }

    public void resetMemberStatus() {
        memberStatus = MemberStatus.MEMBER;
    }

    public void handOverGuildLead(Member formerGuildLeader) {
        memberStatus = MemberStatus.GUILD_LEADER;
        formerGuildLeader.resetMemberStatus();
    }
}
