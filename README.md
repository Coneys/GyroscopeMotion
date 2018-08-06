# GyroscopeMotion

GyroscopeMotion is library that allows to use simple and user-friendly DSL syntax to move your Android Views with Gyroscope. You can apply force multiplier, and configure barriers (maximum DP that view should be moved). 


## Usage

1. <b>Single configuration</b> - you can configure shared barriers and force multiplier for any amount of views:

```
   moveWithGyroscope(card2, card3) {
            blockWhen {
                this.viewMovedHorizontallyBy(dip(-30f).toFloat())
                viewMovedHorizontallyBy(dip(22f).toFloat())
                viewMovedVerticallyBy(dip(8f).toFloat())
                viewMovedVerticallyBy(dip(-2f).toFloat())
            }

            applyForce {
                horizontalMultiplier = 1f
                verticalMultiplier = 0f
            }

        }.start(Context, LifecycleOwner,samplingPeriod:Int)
```        
        
2. <b>Multi configuration</b> - you can create many configurations for many views and start them all together (it is better idea then starting them separatyly)
```
val move1 =moveWithGyroscope(card2, card3) {

            blockWhen {
                viewMovedHorizontallyBy(dip(-22f).toFloat())
                viewMovedHorizontallyBy(dip(22f).toFloat())
                viewMovedVerticallyBy(dip(8f).toFloat())
                viewMovedVerticallyBy(dip(-8f).toFloat())
            }

            applyForce {
                horizontalMultiplier = 1f
                verticalMultiplier = 0f
            }

        }


        val move2 = moveWithGyroscope(card) {

            blockWhen {
                viewMovedHorizontallyBy(dip(-4f).toFloat())
                viewMovedHorizontallyBy(dip(4f).toFloat())
                viewMovedVerticallyBy(dip(22f).toFloat())
                viewMovedVerticallyBy(dip(-22f).toFloat())
            }

            applyForce {
                horizontalMultiplier = 0f
                verticalMultiplier = 1f
            }

        }

        startMovingWithGyroscope(Context, LifecycleOwner, move, move2)
```

## Additional info
1. You always should merge yours connection to Gyroscope into one call (Multi configuration example).
2. Gyroscope will be observe only between onResume - onPause of LifecycleOwner that you will provide
3. When calling start or startMovingWithGyroscope you can provide samplingPeriod. Default = SENSOR_DELAY_GAME

## Adding library to your project 

Add it to your main build.gradle:
```gradle
allprojects {
    repositories {
        maven { url "https://jitpack.io" }
    }
}
```
and into module build.gradle:

```gradle
dependencies {
    implementation 'com.github.coneys:GyroscopeMotion:{latest version}'
}
```

### How blockWhen works
You can specify range that your view can move with gyroscope. If you won't specify any barrier, it will move freely.
Positive value will block right(X-axis)/bottom(Y-axis).
Negative values will block left(X-axis)/top(Y-axis). 
This means, that in example number 1 views can be moved 22 DP to right, 30 DP to left, 8 DP to bottom and 2DP to top.


<b>If you found error or you think that library could be upgraded in any fashion, please created right issue. </b>
  
