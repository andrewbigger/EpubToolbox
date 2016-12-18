package com.biggerconcept.epubtoolbox.features;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    com.biggerconcept.epubtoolbox.features.PackFeature.class,
    com.biggerconcept.epubtoolbox.features.AllCheckFeature.class,
    com.biggerconcept.epubtoolbox.features.UnpackFeature.class,
    com.biggerconcept.epubtoolbox.features.ActionRecordAndReplayFeature.class,
    com.biggerconcept.epubtoolbox.features.FileSizeCheckFeature.class,
    com.biggerconcept.epubtoolbox.features.OsArtefactRemovalFeature.class,
    com.biggerconcept.epubtoolbox.features.EpubCheckFeature.class,
    com.biggerconcept.epubtoolbox.features.ExportLogFeature.class,
    com.biggerconcept.epubtoolbox.features.CollectionModeToggleFeature.class,
    com.biggerconcept.epubtoolbox.features.ClearConsoleFeature.class,
    com.biggerconcept.epubtoolbox.features.RepeatFeature.class,
    com.biggerconcept.epubtoolbox.features.ItunesMetadataRemovalFeature.class,
    com.biggerconcept.epubtoolbox.features.ImageCheckFeature.class,
    com.biggerconcept.epubtoolbox.features.PickFeature.class
})

public class _IntegrationSuite { }
