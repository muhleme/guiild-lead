package io.guildlead.domain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

import static io.guildlead.domain.MemberStatus.GUILD_LEADER;

@Getter
public class Guild {

    private final String name;
    private final String server;
    private final List<Member> guildMembers = new ArrayList<>();

    public Guild(String name, String server, String guildMasterNickName, String characterName) {
        this.name = name;
        this.server = server;
        Member guildMasterMember = new Member(guildMasterNickName, characterName, server);
        guildMasterMember.setMemberStatus(GUILD_LEADER);
        this.guildMembers.add(guildMasterMember);
    }

    public Member getGuildLeader() {
        return guildMembers.stream()
                .filter(x -> GUILD_LEADER.equals(x.getMemberStatus()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No guild leader found"));
    }


}
