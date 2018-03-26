from random import randrange


def main():
    with open("1.level", "w") as f:

        for x in range(0, 1000, 100):
            for y in range(390, 601, 60):
                print("{},{},{}".format(x, y, randrange(0, 3)), file=f)


if __name__ == "__main__":
    main()
