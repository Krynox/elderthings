package me.krynox.elderthings.util;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;

/**
 * A class containing helper functions for working with, or doing logic relating to the state of the world.
 */
public class LevelUtil {

    /**
     * Checks each block in a line in some direction from an initial position (not including the initial pos),
     * applies f to the checked block, and collects the results with merge.
     * Like a weird, specialised fold with side effects. Maybe would be better implemented as a proper right fold?
     *
     * NB: f will perform its side effects regardless of whether the loop terminates with PASS or FAIL.
     *
     * @param f A function which returns an X and possibly does some side effects for each checked block.
     * @param shouldStop A function which decides when to stop checking. Intended to be pure.
     * @param maxDist The maximum number of blocks to check before giving up.
     * @param merge A function X -> X -> X which combines X's in some way. Intended to be pure.
     * @param level The minecraft level.
     * @param initialPos The initial BlockPos to start from.
     * @param dir The direction to check in.
     * @return Maybe a final value of X, or not.
     * @param <X> Some type parameter. In many situations this will be an optional.
     */
    public static <X> Optional<X> forEachBlockInLine(BiFunction<Level, BlockPos, X> f, BiFunction<Level, BlockPos, TriResult> shouldStop, int maxDist, BinaryOperator<X> merge, Level level, BlockPos initialPos, Direction dir) {
        int dist = 0; // A counter to keep track of how many blocks have been checked.
        BlockPos nextPos = initialPos.relative(dir); // The position of the next block to check. Initially one over from the root position.
        ArrayList<X> results = new ArrayList<>(); // List for collecting the results of applying f to each checked block.

        while(dist < maxDist) {
            switch (shouldStop.apply(level, nextPos)) {
                case STOP_FAIL -> {
                    return Optional.empty();
                }

                case STOP_PASS -> { // Add the final result then finish.
                    results.add(f.apply(level, nextPos));
                    return  results.stream().reduce(merge);
                }
                case CONTINUE -> {
                    results.add(f.apply(level, nextPos));
                    nextPos = nextPos.relative(dir);
                    dist++;
                }
            }
        }

        // If we leave the loop then it's because we hit the distance limit, which counts as a failure, so return empty.
        return Optional.empty();
    }


}
