package io.guildlead.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.guildlead.domain.GameCharacterType.ALT_CHARACTER;
import static io.guildlead.domain.GameCharacterType.MAIN_CHARACTER;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

class MemberTest {

    @Test
    public void testMemberCreation(){
        Member member = new Member("Marcel", "Akatosh", "EU-Blackrock");

        assertThat(member.getNickName(), is("Marcel"));

        GameCharacter mainCharacter = member.getGameCharacters().stream()
                .filter(character -> MAIN_CHARACTER.equals(character.getCharacterType()))
                .findFirst()
                .orElseGet(Assertions::fail);
        assertThat(mainCharacter.getName(), is("Akatosh"));
        assertThat(mainCharacter.getServer(), is("EU-Blackrock"));
    }

    @Test
    public void addAltCharacter(){
        Member member = new Member("Ulf", "Leyras", "EU-Blackhand");
        member.addAltCharacter("Athuron", "EU-Onyxia");

        GameCharacter mainCharacter = member.getGameCharacters().stream()
                .filter(character -> MAIN_CHARACTER.equals(character.getCharacterType()))
                .findFirst()
                .orElseGet(Assertions::fail);

        GameCharacter altCharacter = member.getGameCharacters().stream()
                .filter(character -> ALT_CHARACTER.equals(character.getCharacterType()))
                .findFirst()
                .orElseGet(Assertions::fail);

        assertThat(member.getGameCharacters(), hasSize(2));
        assertThat(mainCharacter.getName(), is("Leyras"));
        assertThat(mainCharacter.getServer(), is("EU-Blackhand"));
        assertThat(altCharacter.getName(), is("Athuron"));
        assertThat(altCharacter.getServer(), is("EU-Onyxia"));
    }

    @Test
    public void testHandGuildLeadHandOver(){
        Member oldGuildLeader = new Member("Tim", "Flase", "EU-Aegwynn");
        Member newGuildLeader = new Member("Simon", "Gonpo", "EU-Aegwynn");

        newGuildLeader.handOverGuildLead(oldGuildLeader);

        assertThat(oldGuildLeader.getMemberStatus(), is(MemberStatus.MEMBER));
        assertThat(newGuildLeader.getMemberStatus(), is(MemberStatus.GUILD_LEADER));

    }

}