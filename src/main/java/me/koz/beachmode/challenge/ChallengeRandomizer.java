package me.koz.beachmode.challenge;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ChallengeRandomizer {

    public ArrayList<ChallengeType> challengeTypes = new ArrayList<>();
    private final Random random = new Random();

    public List<ChallengeType> randomize(){

        challengeTypes.add(ChallengeType.BLOCK_MINED);
        challengeTypes.add(ChallengeType.BLOCK_PLACED);
        challengeTypes.add(ChallengeType.DIAMOND_MINED);
        challengeTypes.add(ChallengeType.EMERALD_MINED);
        challengeTypes.add(ChallengeType.COW_KILLED);
        challengeTypes.add(ChallengeType.GOLD_MINED);
        challengeTypes.add(ChallengeType.PLAYER_KILLED);
        challengeTypes.add(ChallengeType.IRON_MINED);
        challengeTypes.add(ChallengeType.GOLD_MINED);
        challengeTypes.add(ChallengeType.ZOMBIE_KILLED);
        challengeTypes.add(ChallengeType.SKELETON_KILLED);

        List<ChallengeType> toReturn = new ArrayList<>();

        int first;
        int second;
        int third;

        do {
            first = random.nextInt(challengeTypes.size());
            second = random.nextInt(challengeTypes.size());
            third = random.nextInt(challengeTypes.size());
        } while (first == second || first == third);

        toReturn.add(challengeTypes.get(first));
        toReturn.add(challengeTypes.get(second));
        toReturn.add(challengeTypes.get(third));

        return toReturn;
    }
}
