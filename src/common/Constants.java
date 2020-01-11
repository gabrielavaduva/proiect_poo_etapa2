package common;

public class Constants {
    // start constants
    public static final int INITIAL_XP = 0;
    public static final int INITIAL_LEVEL = 0;
    public static final int XP_MAX = 200;
    public static final int XP_BONUS = 40;
    public static final int LEVEL_MIN = 250;
    public static final int LEVEL_UP = 50;
    public static final int ON_WOODS_PA = 6;
    public static final int NOT_ON_WOODS_PA = 3;
    public static final float DRAIN_MUL = 0.3f;
    public static final int NO_CRITICAL_BS = 3;

    // HP - initial
    public static final int PY_HP = 500;
    public static final int PY_HP_LEVEL_BONUS = 50;
    public static final int KN_HP = 900;
    public static final int KN_HP_LEVEL_BONUS = 80;
    public static final int WI_HP = 400;
    public static final int WI_HP_LEVEL_BONUS = 30;
    public static final int RO_HP = 600;
    public static final int RO_HP_LEVEL_BONUS = 40;
    // Land/Map constants
    public static final int NO_PLAYER = -1;


    // base damage
    // fireblast
    public static final int FB_DMG = 350;
    public static final int FB_DMG_LEVEL = 50;

    // ignite damage
    public static final int IG_DMG = 150;
    public static final int IG_DMG_LEVEL = 20;

    public static final int IG_OT_DMG = 50;
    public static final int IG_OT_DMG_LEVEL = 30;

    // execute damage
    public static final int EX_DMG = 200;
    public static final int EX_DMG_LEVEL = 30;

    public static final float EX_HP_LIMIT = 0.2f;
    public static final float EX_PERCENT = 0.01f;
    public static final float EX_MAX_PERCENT = 0.4f;

    // slam damage
    public static final int SL_DMG = 100;
    public static final int SL_DMG_LEVEL = 40;

    // drain
    public static final float DR_PERCENT = 0.2f;
    public static final float DR_PERCENT_LEVEL = 0.05f;

    // deflect
    public static final float DE_PERCENT = 0.35f;
    public static final float DE_PERCENT_LEVEL = 0.02f;
    public static final float DE_MAX_PERCENT = 0.70f;

    // backstab damage
    public static final int BS_DMG = 200;
    public static final int BS_DMG_LEVEL = 20;
    public static final float BS_ON_WOODS = 1.5f;

    // paralysis
    public static final int PA_DMG = 40;
    public static final int PA_DMG_LEVEL = 10;



    // Pyromancer race modifiers Fireblast
    public static final float FB_RM_ROGUE = 0.8f;
    public static final float FB_RM_KNIGHT = 1.2f;
    public static final float FB_RM_PYROMANCER = 0.9f;
    public static final float FB_RM_WIZARD = 1.05f;

    // Pyromancer ignite
    public static final float IG_RM_ROGUE = 0.8f;
    public static final float IG_RM_KNIGHT = 1.2f;
    public static final float IG_RM_PYROMANCER = 0.9f;
    public static final float IG_RM_WIZARD = 1.0f;

    // Knight execute
    public static final float EX_RM_ROGUE = 1.15f;
    public static final float EX_RM_KNIGHT = 1.0f;
    public static final float EX_RM_PYROMANCER = 1.1f;
    public static final float EX_RM_WIZARD = 0.8f;

    // Knight slam
    public static final float SL_RM_ROGUE = 0.8f;
    public static final float SL_RM_KNIGHT = 1.2f;
    public static final float SL_RM_PYROMANCER = 0.9f;
    public static final float SL_RM_WIZARD = 1.05f;

    // Wizard drain
    public static final float DR_RM_ROGUE = 0.8f;
    public static final float DR_RM_KNIGHT = 1.2f;
    public static final float DR_RM_PYROMANCER = 0.9f;
    public static final float DR_RM_WIZARD = 1.05f;

    // Wizard deflect
    public static final float DE_RM_ROGUE = 1.2f;
    public static final float DE_RM_KNIGHT = 1.4f;
    public static final float DE_RM_PYROMANCER = 1.3f;

    // Rogue backstab
    public static final float BS_RM_ROGUE = 1.2f;
    public static final float BS_RM_KNIGHT = 0.9f;
    public static final float BS_RM_PYROMANCER = 1.25f;
    public static final float BS_RM_WIZARD = 1.25f;

    // Rogue paralysis
    public static final float PA_RM_ROGUE = 0.9f;
    public static final float PA_RM_KNIGHT = 0.8f;
    public static final float PA_RM_PYROMANCER = 1.2f;
    public static final float PA_RM_WIZARD = 1.25f;

    // LAND MODIFIERS
    public static final float PYROMANCER_VOLCANIC_DMG = 1.25f;
    public static final float KNIGHT_LAND_DMG = 1.15f;
    public static final float WIZARD_DESERT_DMG = 1.1f;
    public static final float ROGUE_WOODS_DMG = 1.15f;
}
