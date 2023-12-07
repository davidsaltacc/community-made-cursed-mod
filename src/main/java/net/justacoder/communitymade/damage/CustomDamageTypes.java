package net.justacoder.communitymade.damage;

import net.minecraft.entity.damage.DamageType;

public abstract class CustomDamageTypes {

    public static final CustomDamageSource CANCER = new CustomDamageSource(new DamageType("cancer", 0f));
    public static final CustomDamageSource TOOL_SUICIDE = new CustomDamageSource(new DamageType("tool_suicide", 0f));

    public static final CustomDamageSource UNKNOWN_CAUSE = new CustomDamageSource(new DamageType("unknown_cause", 0f)); // used to prevent errors with custom damage sources not being in the registry

}
