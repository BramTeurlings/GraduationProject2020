package nl.brickx.data.dagger

import android.content.Context
import javax.inject.Qualifier

/**
 *
 * Annotation used to mark Application [Context] instances
 */
@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class DataContext