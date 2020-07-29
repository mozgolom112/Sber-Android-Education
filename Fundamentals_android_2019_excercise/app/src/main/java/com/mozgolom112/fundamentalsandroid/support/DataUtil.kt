package com.mozgolom112.fundamentalsandroid.support

import com.mozgolom112.fundamentalsandroid.R
import com.mozgolom112.fundamentalsandroid.models.Movie

object DataUtil {

    fun generateMovies(): MutableList<Movie> =
        mutableListOf(
            Movie(
                "Boys Over Flowers",
                R.drawable.boys_over_flowers_poster,
                "Чан-ди - обычная девушка, семья которой заведует химчисткой. Однажды она относила одежду в элитную школу и почти случайно спасла одного ученика. За это ей присудили стипендию в эту самую школу. В школе она сталкивается с богатенькими избалованными парнями. Эти парни известны как F4, и они любят устраивать неприятности всем, кто им не по нраву. Чан-ди, известная своим острым язычком, навлекает на себя гнев ребят. Теперь её ждёт расплата."
            ),Movie(
                "Kill me, heal me",
                R.drawable.kill_me_heal_me_poster,
                "История взаимоотношений мужчины с расстройством множественной личности (у него их семь) и женщины-психиатра."
            ),Movie(
                "Goblin",
                R.drawable.goblin_poster,
                "Бессмертный демон токкэби много лет живет среди смертных и порядком устал от жизни. Но если ты волшебное существо, есть лишь один способ поставить точку и покинуть бренный мир - жениться на смертной. Избранницей демона становится девушка, которая может видеть призраков. А ангел смерти, чья задача провожать души умерших в загробный мир, тем временем потерял память."
            ),Movie(
                "Sigeuneol",
                R.drawable.sigeuneol_poster,
                "Детектив из настоящего находит странную рацию, по которой он может общаться с детективом из прошлого. Вместе они начинают распутывать сложные криминальные дела, в том числе дело легендарного хвасонского серийного убийцы."
            ),Movie(
                "Heelreo",
                R.drawable.heelreo_poster,
                "Ким Мун-хо - звезда среди репортеров и объект зависти журналистов, а так же брат владельца огромной медиакомпании. Из-за событий, произошедших в детстве, он чувствует вину перед Чхэ Ён-щин. Чтобы искупить свои грехи, парень пытается помочь ей превратиться в известного репортера. Но в какой-то момент времени повзрослевшая Ён-щин начинает затрагивать его сердце и убеждения."
            ),Movie(
                "I'm Not a Robot",
                R.drawable.i_m_not_a_robot_poster,
                "У парня никогда не было отношений с девушками, потому что у него аллергия на людей. Однажды он встречает женоробота и влюбляется в неё."
            ),Movie(
                "The K2",
                R.drawable.the_k2_poster,
                "Ким Джэ-ха - бывший солдат-наёмник, его также зовут К2. Жена кандидата в президенты нанимает его телохранителем."
            )
        )
}
