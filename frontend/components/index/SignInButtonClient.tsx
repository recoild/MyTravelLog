"use client";

import { useSession, signIn, signOut } from "next-auth/react";
import Image from "next/image";
import Link from "next/link";

import { Button } from "@/components/ui/button";

export function SignInButton() {
    const { data: session, status } = useSession();
    console.log(session, status);

    if (status === "loading") {
        return <></>;
    }

    if (status === "authenticated") {
        return (
            <Link href={`/dashboard`}>
                <Image
                    src={session.user?.image ?? "/logo512.png"}
                    width={32}
                    height={32}
                    alt="Your Name"
                />
            </Link>
        );
    }

    return <Button onClick={() => signIn()}>로그인</Button>;
}

export function SignOutButton() {
    const { data: session, status } = useSession();

    if (status === "loading") {
        return <></>;
    }
    if (status === "authenticated") {
        return (
            <Button onClick={() => signOut()}>
                {session?.user?.name}님 로그아웃
            </Button>
        );
    }

    return <></>;
}
