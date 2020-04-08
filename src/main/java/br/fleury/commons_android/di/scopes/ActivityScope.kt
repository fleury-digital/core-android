package br.com.fleury.commons.di.scopes

import javax.inject.Scope
import kotlin.annotation.Retention


@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ActivityScope