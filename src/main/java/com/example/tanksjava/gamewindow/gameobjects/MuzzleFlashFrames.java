package com.example.tanksjava.gamewindow.gameobjects;

import java.util.ArrayList;
import java.util.List;

public class MuzzleFlashFrames {

    private final  String muzzleFlashStage1=URLStringsOfAssets.playerMuzzleFlashGraphicAsset1;
    private final  String muzzleFlashStage2=URLStringsOfAssets.playerMuzzleFlashGraphicAsset2;
    private final String  muzzleFlashStage3=URLStringsOfAssets.playerMuzzleFlashGraphicAsset3;

    private List<MuzzleFlash> muzzleFlashStages;

    public MuzzleFlashFrames() {
        muzzleFlashStages=new ArrayList<>();
        muzzleFlashStages.add(new MuzzleFlash(0,muzzleFlashStage1,20,25,false));
        muzzleFlashStages.add(new MuzzleFlash(0,muzzleFlashStage2,16,28,false));
        muzzleFlashStages.add(new MuzzleFlash(0,muzzleFlashStage3,21,38,false));

    }

    public List<MuzzleFlash> getMuzzleFlashStages() {
        return muzzleFlashStages;
    }
}
