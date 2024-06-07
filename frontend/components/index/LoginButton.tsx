import Link from "next/link";

export default async function LoginButton() {
    return (
        <div>
            <Link href="/springbootserver/oauth2/authorization/google">
                <button className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">
                    로그인
                </button>
            </Link>
        </div>
    );
}
