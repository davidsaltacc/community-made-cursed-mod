package net.justacoder.communitymade.damage;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.TagKey;

public class CustomDamageSource extends DamageSource {

    public DamageType type;

    public CustomDamageSource(DamageType type) {
        super(null);
        this.type = type;
    }

    @Override
    public boolean isIn(TagKey<DamageType> tag) {
        return false; // uhm, IDK I do not thingk we need this
    }

    @Override
    public boolean isOf(RegistryKey<DamageType> typeKey) {
        return false; // this neither
    }

    @Override
    public DamageType getType() {
        return type;
    }

    @Override
    public RegistryEntry<DamageType> getTypeRegistryEntry() {
        return null; // this neither
    }
}
