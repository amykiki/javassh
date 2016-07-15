package com.github.amysue.generics;

/**
 * Created by Amysue on 2016/7/5.
 */
public class TestMain {
    public static void main(String[] args) {
        Holder<? extends Fruit> fruitHolder = new Holder<>();
        Holder<Fruit> fruitHolder1 = new Holder<>();
        Holder<Apple> appleHolder = new Holder<>(new Apple());
        Holder<FujiApple> fujiAppleHolder = new Holder<>(new FujiApple());
//        fruit = fujiAppleHolder;
        fruitHolder = appleHolder;
        Pear pear = new Pear();
        Fruit fruit = new Fruit();
        System.out.println(fruitHolder.get());
        System.out.println(pear);
        System.out.println(fujiAppleHolder.get());
        Holder<? super Apple> appleHolder1 = new Holder<>();
        appleHolder1 = fruitHolder1;
        appleHolder1.set(new FujiApple());
        System.out.println(appleHolder1.get());
        Fruit f1 = (Fruit) appleHolder1.get();
        System.out.println(f1);
        FujiApple fuji = (FujiApple) appleHolder1.get();
        System.out.println(fuji);
        Apple f2 = (Apple)fruitHolder.get();
        System.out.println(f2);
    }
}
