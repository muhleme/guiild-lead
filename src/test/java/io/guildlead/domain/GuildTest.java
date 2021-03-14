package io.guildlead.domain;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class GuildTest {

    @Test
    public void setUpGuild(){
        Guild guild = new Guild("Less is More", "EU-Onyxia", "Marcel", "Xentos");

        assertThat(guild.getName(), is("Less is More"));
        assertThat(guild.getServer(), is("EU-Onyxia"));
        assertThat(guild.getGuildLeader().getNickName(), is("Marcel"));
        assertThat(guild.getGuildLeader().getGameCharacters(), hasSize(1));
    }

}